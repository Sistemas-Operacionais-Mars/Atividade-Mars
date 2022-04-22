#Syscall de impressão de string

.macro SyscallPrintString (%texto_param)
    .data
        texto: .asciiz %texto_param
    .text
        li $v0, 4
        la $a0, texto
        syscall
.end_macro

#Syscalls de fim de programa

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

.macro SyscallFork (%valor_para_a0)
    li $a0, %valor_para_a0
	li $v0, 17
	syscall
.end_macro

.macro SycallProcessChange

.end_macro

.macro SycallProcessTerminate

.end_macro