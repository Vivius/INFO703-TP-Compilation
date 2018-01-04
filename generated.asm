DATA SEGMENT
	case1 DD
	case2 DD
	case3 DD
	case4 DD
	case5 DD
	case6 DD
	case7 DD
	case8 DD
	case9 DD
	tour DD
	caseSelectionne DD
DATA ENDS
CODE SEGMENT
	mov eax, 1
	push eax
	sub eax, eax
	pop ebx
	sub eax, ebx
	mov case1, eax
	mov eax, 1
	push eax
	sub eax, eax
	pop ebx
	sub eax, ebx
	mov case2, eax
	mov eax, 1
	push eax
	sub eax, eax
	pop ebx
	sub eax, ebx
	mov case3, eax
	mov eax, 1
	push eax
	sub eax, eax
	pop ebx
	sub eax, ebx
	mov case4, eax
	mov eax, 1
	push eax
	sub eax, eax
	pop ebx
	sub eax, ebx
	mov case5, eax
	mov eax, 1
	push eax
	sub eax, eax
	pop ebx
	sub eax, ebx
	mov case6, eax
	mov eax, 1
	push eax
	sub eax, eax
	pop ebx
	sub eax, ebx
	mov case7, eax
	mov eax, 1
	push eax
	sub eax, eax
	pop ebx
	sub eax, ebx
	mov case8, eax
	mov eax, 1
	push eax
	sub eax, eax
	pop ebx
	sub eax, ebx
	mov case9, eax
	mov eax, 1
	mov tour, eax
START_WHILE_1:
	mov eax, tour
	push eax
	mov eax, 9
	pop ebx
	sub eax, ebx
	jl FALSE_GTEQ_2
	mov eax, 1
	jmp END_GTEQ2
FALSE_GTEQ_2:
	mov eax, 0
END_GTEQ2:
	jz END_WHILE_1
	in eax
	mov caseSelectionne, eax
	mov eax, 2
	push eax
	mov eax, tour
	pop ebx
	mov ecx, eax
	div ecx, ebx
	mul ecx, ebx
	sub eax, ecx
	push eax
	mov eax, 0
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_4
	mov eax, 1
	jmp END_EQ4
FALSE_EQ_4:
	mov eax, 0
END_EQ4:
	jz ELSE_3
	mov eax, caseSelectionne
	push eax
	mov eax, 1
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_6
	mov eax, 1
	jmp END_EQ6
FALSE_EQ_6:
	mov eax, 0
END_EQ6:
	jz ELSE_5
	mov eax, 0
	mov case1, eax
	jmp END_IF_5
ELSE_5:
END_IF_5:
	mov eax, caseSelectionne
	push eax
	mov eax, 2
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_8
	mov eax, 1
	jmp END_EQ8
FALSE_EQ_8:
	mov eax, 0
END_EQ8:
	jz ELSE_7
	mov eax, 0
	mov case2, eax
	jmp END_IF_7
ELSE_7:
END_IF_7:
	mov eax, caseSelectionne
	push eax
	mov eax, 3
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_10
	mov eax, 1
	jmp END_EQ10
FALSE_EQ_10:
	mov eax, 0
END_EQ10:
	jz ELSE_9
	mov eax, 0
	mov case3, eax
	jmp END_IF_9
ELSE_9:
END_IF_9:
	mov eax, caseSelectionne
	push eax
	mov eax, 4
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_12
	mov eax, 1
	jmp END_EQ12
FALSE_EQ_12:
	mov eax, 0
END_EQ12:
	jz ELSE_11
	mov eax, 0
	mov case4, eax
	jmp END_IF_11
ELSE_11:
END_IF_11:
	mov eax, caseSelectionne
	push eax
	mov eax, 5
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_14
	mov eax, 1
	jmp END_EQ14
FALSE_EQ_14:
	mov eax, 0
END_EQ14:
	jz ELSE_13
	mov eax, 0
	mov case5, eax
	jmp END_IF_13
ELSE_13:
END_IF_13:
	mov eax, caseSelectionne
	push eax
	mov eax, 6
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_16
	mov eax, 1
	jmp END_EQ16
FALSE_EQ_16:
	mov eax, 0
END_EQ16:
	jz ELSE_15
	mov eax, 0
	mov case6, eax
	jmp END_IF_15
ELSE_15:
END_IF_15:
	mov eax, caseSelectionne
	push eax
	mov eax, 7
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_18
	mov eax, 1
	jmp END_EQ18
FALSE_EQ_18:
	mov eax, 0
END_EQ18:
	jz ELSE_17
	mov eax, 0
	mov case7, eax
	jmp END_IF_17
ELSE_17:
END_IF_17:
	mov eax, caseSelectionne
	push eax
	mov eax, 8
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_20
	mov eax, 1
	jmp END_EQ20
FALSE_EQ_20:
	mov eax, 0
END_EQ20:
	jz ELSE_19
	mov eax, 0
	mov case8, eax
	jmp END_IF_19
ELSE_19:
END_IF_19:
	mov eax, caseSelectionne
	push eax
	mov eax, 9
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_22
	mov eax, 1
	jmp END_EQ22
FALSE_EQ_22:
	mov eax, 0
END_EQ22:
	jz ELSE_21
	mov eax, 0
	mov case9, eax
	jmp END_IF_21
ELSE_21:
END_IF_21:
	jmp END_IF_3
ELSE_3:
	mov eax, caseSelectionne
	push eax
	mov eax, 1
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_24
	mov eax, 1
	jmp END_EQ24
FALSE_EQ_24:
	mov eax, 0
END_EQ24:
	jz ELSE_23
	mov eax, 1
	mov case1, eax
	jmp END_IF_23
ELSE_23:
END_IF_23:
	mov eax, caseSelectionne
	push eax
	mov eax, 2
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_26
	mov eax, 1
	jmp END_EQ26
FALSE_EQ_26:
	mov eax, 0
END_EQ26:
	jz ELSE_25
	mov eax, 1
	mov case2, eax
	jmp END_IF_25
ELSE_25:
END_IF_25:
	mov eax, caseSelectionne
	push eax
	mov eax, 3
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_28
	mov eax, 1
	jmp END_EQ28
FALSE_EQ_28:
	mov eax, 0
END_EQ28:
	jz ELSE_27
	mov eax, 1
	mov case3, eax
	jmp END_IF_27
ELSE_27:
END_IF_27:
	mov eax, caseSelectionne
	push eax
	mov eax, 4
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_30
	mov eax, 1
	jmp END_EQ30
FALSE_EQ_30:
	mov eax, 0
END_EQ30:
	jz ELSE_29
	mov eax, 1
	mov case4, eax
	jmp END_IF_29
ELSE_29:
END_IF_29:
	mov eax, caseSelectionne
	push eax
	mov eax, 5
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_32
	mov eax, 1
	jmp END_EQ32
FALSE_EQ_32:
	mov eax, 0
END_EQ32:
	jz ELSE_31
	mov eax, 1
	mov case5, eax
	jmp END_IF_31
ELSE_31:
END_IF_31:
	mov eax, caseSelectionne
	push eax
	mov eax, 6
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_34
	mov eax, 1
	jmp END_EQ34
FALSE_EQ_34:
	mov eax, 0
END_EQ34:
	jz ELSE_33
	mov eax, 1
	mov case6, eax
	jmp END_IF_33
ELSE_33:
END_IF_33:
	mov eax, caseSelectionne
	push eax
	mov eax, 7
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_36
	mov eax, 1
	jmp END_EQ36
FALSE_EQ_36:
	mov eax, 0
END_EQ36:
	jz ELSE_35
	mov eax, 1
	mov case7, eax
	jmp END_IF_35
ELSE_35:
END_IF_35:
	mov eax, caseSelectionne
	push eax
	mov eax, 8
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_38
	mov eax, 1
	jmp END_EQ38
FALSE_EQ_38:
	mov eax, 0
END_EQ38:
	jz ELSE_37
	mov eax, 1
	mov case8, eax
	jmp END_IF_37
ELSE_37:
END_IF_37:
	mov eax, caseSelectionne
	push eax
	mov eax, 9
	pop ebx
	sub eax, ebx
	jnz FALSE_EQ_40
	mov eax, 1
	jmp END_EQ40
FALSE_EQ_40:
	mov eax, 0
END_EQ40:
	jz ELSE_39
	mov eax, 1
	mov case9, eax
	jmp END_IF_39
ELSE_39:
END_IF_39:
END_IF_3:
	mov eax, case1
	out eax
	mov eax, case2
	out eax
	mov eax, case3
	out eax
	mov eax, case4
	out eax
	mov eax, case5
	out eax
	mov eax, case6
	out eax
	mov eax, case7
	out eax
	mov eax, case8
	out eax
	mov eax, case9
	out eax
	mov eax, tour
	push eax
	mov eax, 1
	pop ebx
	add eax, ebx
	mov tour, eax
	jmp START_WHILE_1
END_WHILE_1:
CODE ENDS
