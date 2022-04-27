.include "macros.asm"

.data
# produtor consumidor => cada um vai ser um processo
# alternancia entre processos deve ser pelo timer
# criacao do semaforo (mutex,empty e full) devem ser feitas antes da execucao dos processo de produtor e consumidor 
# buffer de dados compartilhado deve ser uma pilha 
# lw e sw para inserir e remover do buffer (observar estado da pilha durante a execucao)

	empty: .word 100
    full: .word 0
    mutex: .word 1

	buffer:
		.align 2
		.space 128

.text
	CreateSemaphore(empty)#empty	

	CreateSemaphore(full)#full

	CreateSemaphore(mutex)#mutex
	
	#criação dos processos com prioridade
	SyscallFork(Producer, 1)
	SyscallFork(Consumer, 2)
	#escalonando o primeiro processo
	SyscallProcessChange
	 
	li $t2, 128
		
Producer:
	move $t0, $zero #indice do array
	move $t1, $zero #valor a ser inserido	
	loop1:
        DownSemaphore(empty)
        DownSemaphore(mutex)
        sw $t1, buffer($t0)
        addi $t0, $t0, 4 #passa para o proximo indice
        addi $t1, $t1, 1 #item $t1 incrementa mais 1
        UpSemaphore(mutex)
        UpSemaphore(full)	
       	beq $t0, $t2, fim1
		SyscallProcessChange
		j loop1
	fim1:	SyscallProcessTerminate
           
Consumer:
	move $t0, $zero #indice do array
	loop2:
        DownSemaphore(full)
        DownSemaphore(mutex)
        UpSemaphore(mutex)
        UpSemaphore(empty)
        lw $a0, buffer($t0)
        addi $t0, $t0, 4 #passa para o proximo indice	
        beq $t0, $t2, fim2
		SyscallProcessChange
		j loop2
	fim2:	SyscallProcessTerminate
        
        
