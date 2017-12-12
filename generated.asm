DATA SEGMENT
	m DD
DATA ENDS
CODE SEGMENT
	mov eax, 5
	push eax
	mov eax, 3
	pop ebx
	add eax, ebx
	mov m, eax
CODE ENDS
