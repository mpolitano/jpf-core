
function runJPF(){
for p in $projects
	do
		for c in $class 
		do
			for d in $driver 
			do
				java -jar build/RunJPF.jar src/examples/$p/$c/$d.jpf > result.txt
			done
		done
	done
}


function changeScope(){
	pushd src/examples/
	find ./ -type f -exec sed -i '' 's/scope=10/scope=2/g' {} \;
	popd
}

changeScope;

projects="builders"
class=schedule
driver="Schedule_all Schedule"
runJPF

class=treemap
driver="TreeMap_pred_all TreeMap_pred"
runJPF

class=bintree
driver="BinTree_pred_all BinTree_pred"
runJPF

projects="java2/util2"
class=linkedlist
driver="LinkedList_all LinkedList"
runJPF

class=treemap
driver="TreeMap_all TreeMap"
runJPF

class=treeset
driver="TreeSet_all TreeSet"
runJPF


class=hashmap
driver="HashMap"
runJPF