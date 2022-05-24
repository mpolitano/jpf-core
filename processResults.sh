#!/bin/bash 
clean_and_compile() {
    cmd="ant clean clean-auto-tests compile > /dev/null"
    echo ""
    echo "> Cleaning up and compiling project: $cmd"
    bash -c "$cmd"
}

resultsdir=./results-jpf/
tmpfile="processresults.csv.tmp"
[[ -f $tmpfile ]] && rm $tmpfile
[[ -f $tmpfilebuilders ]] && rm $tmpfilebuilders

function process_results() {
    currdir=$resultsdir

    [[ ! -d $currdir ]] && continue;
    gentime=""
    blocksCoverage=""
    branchCoverage=""
    structures=""
    explored=""
    for p in $projects
    do
        [[ ! -d $currdir ]] && echo "No project found in $currdir" && continue;
        for c in $class 
        do
                 currdir=$resultsdir/$p/$c/
            [[ ! -d $currdir ]] && continue;
            for d in $driver
            do
                currdir=$resultsdir/$p/$c/$d/
                [[ ! -d $currdir ]] && continue
                scope=$(ls $currdir)
                for s in $scope
                do    
                [[ $scope == "" ]] && echo "No scope found in $currdir" && continue;
                currdir=$resultsdir/$p/$c/$d/$s
                logfile="$currdir/result.txt"

                class=$(grep $d $logfile)
                blocksCoverage=$(awk -F '  +' '{print $3}' $logfile |cut -d ' ' -f2|grep "[0-9]"|tail -1)
                branchCoverage=$(awk -F '  +' '{print $4}' $logfile |cut -d ' ' -f2|grep "[0-9]"|tail -1)
                gentime=$(grep "time" $logfile | cut -d' ' -f9)
                structures=$(grep "backtracked" $logfile| cut -d',' -f3| cut -d'=' -f2)
                echo "$c,$s, $blocksCoverage,$branchCoverage,$gentime,$structures" >> $tmpfile
                done
            done
        done
    done
    }


echo "Class,Scope,Blocks,Brachs,Time,Structures"
projects="java2/util2"
class=linkedlist
driver="LinkedListAdd_All LinkedListRemove_All LinkedListAdd LinkedListRemove"
process_results
class=treemap
driver="TreeMapAdd_All  TreeMapRemove_All TreeMapAdd TreeMapRemove"
process_results
class=treeset
driver="TreeSetAdd_All  TreeSetRemove_All TreeSetAdd TreeSetRemove"
process_results
class=hashmap
driver="HashMapAdd_All  HashMapRemove_All HashMapAdd HashMapRemove"
driver="HashMapAdd"
process_results
class=hashset
driver="HashSetAdd_All  HashSetRemove_All HashSetAdd HashSetRemove"
process_results


cat $tmpfile | sort -V
rm $tmpfile