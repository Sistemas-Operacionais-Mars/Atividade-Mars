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

.macro SyscallExit2(%valor_para_a0)
    la $a0, %valor_para_a0
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

.macro CreateSemaphore(%endereco_var)
    la a0, %endereco_var
    li $v0,63
	syscall
.end_macro

.macro TerminateSemaphore(%endereco_var)
    la a0, %endereco_var
    li $v0,64
	syscall
.end_macro

.macro DownSemaphore(%endereco_var)
    la a0, %endereco_var
    li $v0,65
	syscall
.end_macro

.macro UpSemaphore(%endereco_var)
    la a0, %endereco_var
    li $v0,66
	syscall
.end_macro