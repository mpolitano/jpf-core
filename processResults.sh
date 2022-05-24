#!/bin/bash 
clean_and_compile() {
    cmd="ant clean clean-auto-tests compile > /dev/null"
    echo ""
    echo "> Cleaning up and compiling project: $cmd"
    bash -c "$cmd"
}

source $BE_EXP_SRC/scripts/common.sh

resultsdir=./results-begen/
tmpfile="processresults.csv.tmp"
tmpfilebuilders="builders.txt"
[[ -f $tmpfile ]] && rm $tmpfile
[[ -f $tmpfilebuilders ]] && rm $tmpfilebuilders

function process_results() {

                    logfile=result.txt

                    gentime=""
                    blocksCoverage=""
                    branchCoverage=""
                    structures=""
                    explored=""

                    class=$(grep -A 1 "system under test" $logfile  | grep -v "system under test")
                    blocksCoverage=$(grep "total" $logfile | cut -d' ' -f20)
                    branchCoverage=$(grep "total" $logfile | cut -d' ' -f30)
                    gentime=$(grep "time" $logfile | cut -d' ' -f9)
                    structures=$(grep "backtracked" $logfile| cut -d',' -f3| cut -d'=' -f2)
                    echo $gentime >> $tmpfilebuilders
                    echo "$class,$blocksCoverage,$branchCoverage,$gentime,$structures" >> $tmpfile
}


echo "Class,Blocks,Brachs,Time,Structures"

process_results

cat $tmpfile | sort -V
# rm $tmpfile

