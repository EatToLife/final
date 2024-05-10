package com.example.eattolife;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Handler uiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        int[] array = {12, 11, 13, 5, 6, 7, 1, 2, 9, 8};

        // 使用分块策略一
        ParallelMergeSortWithFixedBlockSize sorter1 = new ParallelMergeSortWithFixedBlockSize(array, 0, array.length - 1);
        sorter1.start();

        // 使用分块策略二
        ParallelMergeSortWithEqualBlock sorter2 = new ParallelMergeSortWithEqualBlock(array, 0, array.length - 1);
        sorter2.start();
    }

    class ParallelMergeSortWithFixedBlockSize extends Thread {
        private int[] array;
        private int start, end;
        private final int BLOCK_SIZE = 2; // 每个块的大小

        public ParallelMergeSortWithFixedBlockSize(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        public void run() {
            if (end - start < BLOCK_SIZE) {
                Arrays.sort(array, start, end + 1);
            } else {
                int mid = (start + end) / 2;
                ParallelMergeSortWithFixedBlockSize leftSorter = new ParallelMergeSortWithFixedBlockSize(array, start, mid);
                ParallelMergeSortWithFixedBlockSize rightSorter = new ParallelMergeSortWithFixedBlockSize(array, mid + 1, end);

                leftSorter.start();
                rightSorter.start();

                try {
                    leftSorter.join();
                    rightSorter.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                merge(start, mid, end);
            }

            // When sorting is done, update the UI
            if (start == 0 && end == array.length - 1) {
                uiHandler.post(() -> textView.append("\nSorted array using fixed block size: " + Arrays.toString(array)));
            }
        }

        private void merge(int start, int mid, int end) {
            int[] tempArray = new int[end - start + 1];
            int i = start, j = mid + 1, k = 0;

            while (i <= mid && j <= end) {
                if (array[i] <= array[j]) {
                    tempArray[k++] = array[i++];
                } else {
                    tempArray[k++] = array[j++];
                }
            }

            while (i <= mid) {
                tempArray[k++] = array[i++];
            }

            while (j <= end) {
                tempArray[k++] = array[j++];
            }

            for (i = start, k = 0; i <= end; i++, k++) {
                array[i] = tempArray[k];
            }
        }
    }

    class ParallelMergeSortWithEqualBlock extends Thread {
        private int[] array;
        private int start, end;
        private final int NUM_THREADS = 2; // 线程数量

        public ParallelMergeSortWithEqualBlock(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        public void run() {
            if (end - start < NUM_THREADS) {
                Arrays.sort(array, start, end + 1);
            } else {
                Thread[] threads = new Thread[NUM_THREADS];
                int blockSize = (end - start + 1) / NUM_THREADS;
                int remaining = (end - start + 1) % NUM_THREADS;

                int i = start;
                for (int t = 0; t < NUM_THREADS; t++) {
                    int blockSizeForThread = blockSize + (remaining-- > 0 ? 1 : 0);
                    threads[t] = new Thread(new SortRunnable(i, i + blockSizeForThread - 1));
                    threads[t].start();
                    i += blockSizeForThread;
                }

                for (Thread thread : threads) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                merge(start, end - blockSize, end);
            }

            // When sorting is done, update the UI
            if (start == 0 && end == array.length - 1) {
                uiHandler.post(() -> textView.append("\nSorted array using equal block size: " + Arrays.toString(array)));
            }
        }


        private void merge(int start, int mid, int end) {
            int[] tempArray = new int[end - start + 1];
            int i = start, j = mid + 1, k = 0;

            while (i <= mid && j <= end) {
                if (array[i] <= array[j]) {
                    tempArray[k++] = array[i++];
                } else {
                    tempArray[k++] = array[j++];
                }
            }

            while (i <= mid) {
                tempArray[k++] = array[i++];
            }

            while (j <= end) {
                tempArray[k++] = array[j++];
            }

            for (i = start, k = 0; i <= end; i++, k++) {
                array[i] = tempArray[k];
            }
        }

        class SortRunnable implements Runnable {
            private int start, end;

            public SortRunnable(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public void run() {
                Arrays.sort(array, start, end + 1);
            }
        }
    }
}
