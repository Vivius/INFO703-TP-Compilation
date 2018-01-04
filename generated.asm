DATA SEGMENT
	stop DD
	n DD
	u DD
	v DD
	i DD
	t DD
DATA ENDS
CODE SEGMENT
	mov eax, 0
	mov stop, eax
debut_while_1:
	mov eax, stop
	push eax
	mov eax, 0
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_2
	mov eax, 1
	jmp END_EQ2
FALSE_EQ_2:
	mov eax, 0
END_EQ2:
	jz sortie_while_1
	in eax
	mov n, eax
	mov eax, 0
	mov u, eax
	mov eax, 1
	mov v, eax
	mov eax, 2
	mov i, eax
debut_while_3:
	mov eax, i
	push eax
	mov eax, n
	pop ebx
	sub eax, ebx
	jl faux_gteq_4
	mov eax, 1
	jmp sortie_gteq_4
faux_gteq_4:
	mov eax, 0
sortie_gteq_4:
	jz sortie_while_3
	mov eax, u
	push eax
	mov eax, v
	pop ebx
	add eax, ebx
	mov t, eax
	mov eax, v
	mov u, eax
	mov eax, t
	mov v, eax
	mov eax, i
	push eax
	mov eax, 1
	pop ebx
	add eax, ebx
	mov i, eax
	jmp debut_while_3
sortie_while_3:
	mov eax, v
	out eax
	in eax
	mov stop, eax
	jmp debut_while_1
sortie_while_1:
CODE ENDS
