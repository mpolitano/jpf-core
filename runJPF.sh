
function runJPF(){
for p in $projects
	do
		for c in $class 
		do
			for d in $driver 
			do
				for s in $scope 
				do
                echo "SCOPE====== "$s >>result.txt
                changeScope;
				java -jar build/RunJPF.jar src/examples/$p/$c/$d.jpf > result.txt
			done
		done
	done
}


function changeScope(){
	pushd src/examples/
        find ./ -type f -exec sed -i 's/\bscope=[0-9]/scope='$scope'/g' {} \;
    popd
	ant
}

scope="1 2 3 4 5 6 7 8 9 10"
class=$3
driver=$4
projects=$2

projects="builders"
class=schedule
driver="Schedule_all Schedule"
#runJPF

class=treemap
driver="TreeMap_pred_All TreeMap_pred"
# runJPF

class=bintree
driver="BinTree_all BinTree"
#runJPF

projects="java2/util2"
class=linkedlist
driver="LinkedList_All LinkedList"
runJPF

class=treemap
driver="TreeMap_All TreeMap"
runJPF

class=treeset
driver="TreeSet_All TreeSet"
runJPF


class=hashmap
driver="HashMap_All HashMap"
#runJPF
