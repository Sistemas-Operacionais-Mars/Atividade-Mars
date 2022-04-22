#Syscall de impressão de string
.macro SyscallPrintString(%texto)
    li $v0, 4
    la $a0, %texto
    syscall
.end_macro

.macro SyscallExit
	li $v0,10
	syscall
.end_macro

.macro SyscallExit2 (%valor_para_a0)
    li $a0, %valor_para_a0
	li $v0, 17
	syscall
.end_macro

#Syscalls criadas no trabalho

.macro SyscallFork(%endereco) 
    li $v0,60
    la $a0, %endereco
	syscall
.end_macro

.macro SyscallProcessChange
    li $v0,61
	syscall
.end_macro

.macro SyscallProcessTerminate
    li $v0,62
	syscall
.end_macro
