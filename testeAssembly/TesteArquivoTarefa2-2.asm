.include "macros.asm"

.data
.text             
	#criação dos processos
	SyscallFork(Programa1, 1)
	SyscallFork(Programa2, 2)
	SyscallFork(Idle, 0)
	#escalonando o primeiro processo
SyscallProcessChange
	
Idle:
	loop:
	SyscallProcessChange
	j loop	

Programa1:					
		addi $s1, $zero, 1 # valor inicial do contador
		addi $s2, $zero, 10 # valor limite do contador
	loop1: 	addi $s1, $s1, 1
		beq $s1, $s2, fim1
SyscallProcessChange
		j loop1
	fim1:	SyscallProcessTerminate

Programa2: 
		addi $s1, $zero, -1 # valor inicial do contador
		addi $s2, $zero, -10 # valor limite do contador
	loop2: 	addi $s1, $s1, -1
		beq $s1, $s2, fim2
SyscallProcessChange
		j loop2
	fim2:	SyscallProcessTerminate

Programa3: 
		li $s1, 1 # valor inicial do contador
		li $s2, 8 # valor limite do contador
    loop3: 	mul $s1, $s1, 2 #multiplica $s1 por 2 até chegar no valor de $s2
		beq $s1, $s2, fim3
SyscallProcessChange
		j loop3
	fim3:	SyscallProcessTerminate

Programa4: 
		li $s1, 8 # valor inicial do contador
		li $s2, 2 # valor do divisor
        li $s3, 1 # valor limite do contador
    loop4: 	div $s1, $s2 #divide $s1 por $s2 até chegar no valor de $s3
            mflo $s1 #move o resultado temporário para $s1
        beq $s1, $s3, fim4
SyscallProcessChange
		j loop4
	fim4:	SyscallProcessTerminate
