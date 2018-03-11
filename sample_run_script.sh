#! /bin/bash
rm -rf *.out
rm -rf *.csv
rm -rf data*
rm -rf *.pdf
./compile_programs.sh
mkdir data_random data_reverse data_sorted
path=$(pwd)
outfile=my_runs_random.out
# customize the line below for your choice of problem sizes
for n in 320000 640000 1280000; do
# can add more seeds
    for seed in 1; do
        instance=${path}/data_random/rand_${n}_${seed}
        ./random_integers.py $n $seed > $instance
        for implementation in ./run_insertionsort.sh ./run_mergesort.sh ./run_heapsort.sh ./run_sortutility.sh; do
            # customize the output lines for your convenience
            echo "size,$n" >> $outfile
            echo "seed,$seed" >> $outfile
            echo "implementation,$implementation" >> $outfile
            # the > /dev/null makes the sorted output go away
            # the 2>> captures the stderr output
            x="$(echo $implementation | awk -F_ '{print $2}' | awk -F. '{print $1}')"
            cat $instance | $implementation > /dev/null 2>> $outfile
            echo $n, $(cat $outfile | grep -o '[0-9]\+' | tail -n2 | sed -n 1p), $(cat $outfile | grep -o '[0-9]\+' | tail -n2 | sed -n 2p) >> ${x}_random.csv
            echo "---------------------" >> $outfile
        done
    done
done

outfile=my_runs_sorted.out

for n in 320000 640000 1280000; do
        instance=${path}/data_sorted/rand_${n}
        ./sorted_input.py $n > $instance
        for implementation in ./run_insertionsort.sh ./run_mergesort.sh ./run_heapsort.sh ./run_sortutility.sh; do
            # customize the output lines for your convenience
            echo "size,$n" >> $outfile
            echo "implementation,$implementation" >> $outfile
            # the > /dev/null makes the sorted output go away
            # the 2>> captures the stderr output
            x="$(echo $implementation | awk -F_ '{print $2}' | awk -F. '{print $1}')"
            cat $instance | $implementation > /dev/null 2>> $outfile
            echo $n, $(cat $outfile | grep -o '[0-9]\+' | tail -n2 | sed -n 1p), $(cat $outfile | grep -o '[0-9]\+' | tail -n2 | sed -n 2p) >> ${x}_sorted.csv
            echo "---------------------" >> $outfile
        done
done

outfile=my_runs_reverse_sorted.out

for n in 320000 640000 1280000; do
        instance=${path}/data_reverse/rand_${n}
        ./reverse_input.py $n > $instance
        for implementation in ./run_insertionsort.sh ./run_mergesort.sh ./run_heapsort.sh ./run_sortutility.sh; do
            # customize the output lines for your convenience
            echo "size,$n" >> $outfile
            echo "implementation,$implementation" >> $outfile
            # the > /dev/null makes the sorted output go away
            # the 2>> captures the stderr output
            x="$(echo $implementation | awk -F_ '{print $2}' | awk -F. '{print $1}')"
            cat $instance | $implementation > /dev/null 2>> $outfile
            echo $n, $(cat $outfile | grep -o '[0-9]\+' | tail -n2 | sed -n 1p), $(cat $outfile | grep -o '[0-9]\+' | tail -n2 | sed -n 2p) >> ${x}_reverse.csv
            echo "---------------------" >> $outfile
        done
done

# generate plots
Rscript test.R

#  [Last modified: 2018 02 03 at 14:42:31 GMT]
