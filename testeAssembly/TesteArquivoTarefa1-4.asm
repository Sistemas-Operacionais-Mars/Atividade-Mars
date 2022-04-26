.include "macros.asm"

.data
# produtor consumidor => cada um vai ser um processo
# alternancia entre processos deve ser pelo timer
# criacao do semaforo (mutex,empty e full) devem ser feitas antes da execucao dos processo de produtor e consumidor 
# buffer de dados compartilhado deve ser uma pilha 
# lw e sw para inserir e remover do buffer (observar estado da pilha durante a execucao)

CreateSemaphore($a0)


.text 