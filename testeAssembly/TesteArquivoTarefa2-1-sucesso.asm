.include "macros.asm"

.data
.text             
    #criação dos processos com prioridade
	SyscallFork(Idle, 0)
    SyscallFork(Programa1, 1)
	SyscallFork(Programa2, 2)
	#escalonando o primeiro processo
SyscallProcessChange
	
Idle:					
	loop:
NOP
j loop

Programa1:					
		addi $s1, $zero, 1 # valor inicial do contador
		addi $s2, $zero, 10 # valor limite do contador
	loop1: 	addi $s1, $s1, 1
		beq $s1, $s2, fim1
j loop1
	fim1:	SyscallProcessTerminate

Programa2: 
		addi $s1, $zero, -1 # valor inicial do contador
		addi $s2, $zero, -10 # valor limite do contador
	loop2: 	addi $s1, $s1, -1
		beq $s1, $s2, fim2
		j loop2
	fim2:	SyscallProcessTerminate
