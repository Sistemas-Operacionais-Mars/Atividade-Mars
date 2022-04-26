.include "macros.asm"

.data
	leiva: .asciiz "Leiva Casemiro,"
	aviso: .asciiz " esteja avisado! >:c"
.text             
	SyscallPrintString(leiva)
	SyscallPrintString(aviso)
	SyscallExit




