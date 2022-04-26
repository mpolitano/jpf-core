
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
	find ./ -type f -exec sed -i 's/scope=2/scope=5/g' {} \;
	popd
	ant
}

changeScope;

projects="builders"
class=schedule
driver="Schedule_all Schedule"
runJPF

class=treemap
driver="TreeMap_All TreeMap"
runJPF

class=bintree
driver="BinTree_All BinTree"
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