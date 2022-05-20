
function runJPF(){
for p in $projects
	do
		for c in $class 
		do
			for s in $scope 
			do
				for d in $driver 
				do
					echo "========== SCOPE $s =================" >> result.txt
	                changeScope $s
	                java -jar  build/RunJPF.jar src/examples/$p/$c/$d.jpf >> result.txt
				done
			done
		done
	done
}


function changeScope(){
	#Warning;
	pushd src/examples/java2/util2/
        find ./ -type f -exec sed -i 's/\bscope=\([0-9]\)\+/scope='$1'/g' {} \;
    popd
	ant
}

scope="1 2 3 4 5 6 7 8 9 10 11"
#scope=1
# class=$3
# driver=$4
# projects=$2

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
driver="LinkedListAdd_All LinkedListRemove_All LinkedListAdd LinkedListRemove"
runJPF

class=treemap
driver="TreeMapAdd_All  TreeMapRemove_All TreeMapAdd TreeMapRemove"
runJPF

class=treeset
driver="TreeSetAdd_All  TreeSetRemove_All TreeSetAdd TreeSetRemove"
runJPF

class=hashmap
driver="HashMapAdd_All  HashMapRemove_All HashMapAdd HashMapRemove"
#runJPF

class=hashset
driver="HashSetAdd_All  HashSetRemove_All HashSetAdd HashSetRemove"
#runJPF
