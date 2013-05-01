mpjrun.sh -np 1 ParallelHyFlex/dist/ParallelHyFlex.jar
bash moveLogs.sh 0
mpjrun.sh -np 2 ParallelHyFlex/dist/ParallelHyFlex.jar
bash moveLogs.sh 1
mpjrun.sh -np 3 ParallelHyFlex/dist/ParallelHyFlex.jar
bash moveLogs.sh 2
mpjrun.sh -np 4 ParallelHyFlex/dist/ParallelHyFlex.jar
bash moveLogs.sh 3
