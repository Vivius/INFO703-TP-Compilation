DATA SEGMENT
	x DD
	y DD
DATA ENDS
CODE SEGMENT
	mov eax, 2
	push eax
	mov eax, 5
	pop ebx
	mov ecx, eax
	div ecx, ebx
	mul ecx, ebx
	sub eax, ecx
	push eax
	mov eax, 3
	pop ebx
	mul eax, ebx
	mov x, eax
	in eax
	mov y, eax
	mov eax, y
	push eax
	mov eax, x
	pop ebx
	mul eax, ebx
	out eax
CODE ENDS
