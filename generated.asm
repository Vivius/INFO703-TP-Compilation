DATA SEGMENT
DATA ENDS
CODE SEGMENT
	mov eax, 4
	push eax
	mov eax, 2
	push eax
	mov eax, 2
	pop ebx
	mul eax, ebx
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_3
	mov eax, 1
	jmp END_EQ3
FALSE_EQ_3:
	mov eax, 0
END_EQ3:
	jz END_AND_2
	mov eax, 1
	push eax
	mov eax, 1
	push eax
	mov eax, 1
	pop ebx
	div ebx, eax
	mov eax, ebx
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_4
	mov eax, 1
	jmp END_EQ4
FALSE_EQ_4:
	mov eax, 0
END_EQ4:
END_AND_2:
	jz ELSE_1
	mov eax, 1
	out eax
	jmp END_IF_1
ELSE_1:
	mov eax, 0
	out eax
END_IF_1:
CODE ENDS
