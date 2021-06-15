		AREA Palindrome, CODE, READONLY
		ENTRY	;code entry point
		
 		adr r0, STRING	;pointer to the string		
							;count the number of character in STRING 
count 	LDRB r1, [r0], #1	;load first byte and set [r0] to next byte
		CMP r1, #0	;if the next byte 0, get out of this loop (stop counting the number of char)
		ADDNE r3, r3, #1	; count # character in STRING
		BNE count	;keep looping until it reaches to the end (0x00)
		LDR r0, =STRING	;pointer to the string again
		
palin	CMP r2, r3	;not to check same things again (ex)abcba - only need to check ab:ba, abccba - check abc:cba
		BGT	success	;if so, then we have found a palindrome -> go to success branch 			
		LDRB r4, [r0, r2]	;load the current left side char
		LDRB r5, [r0, r3]	;load the current right side char
						;check left side char 
left	CMP R4, #0x41	;if current left side char is not A-Z (ascii code is less than 0x41)
		BLT inc	;increase the left pointer 
		CMP R4, #0x5A	;if char is A-Z
		ADDLE R4, #0x20	; make it lowercase char (insensitive)
		CMP R4, #0x61	;if ascii code is greater than 0x5A and less than 0x61 (Ignore all characters that are not letters)
		BLT inc	;increase the left pointer
		CMP R4, #0x7A	;if it is not A-Z and a-z
		BGT inc	;increase the left pointer (Ignore all characters that are not letters)
	
						;check right side char (do the same thing above for right side char)
right	CMP R5, #0x41	;if current right right char is not A-Z (ascii code is less than 0x41)
		BLT dec	;decrease the right pointer
		CMP R5, #0x5A	;if char is A-Z
		ADDLE R5, #0x20	;make it lowercase char (insensitive)
		CMP R5, #0x61	;if ascii code is greater than 0x5A and less than 0x61 (Ignore all characters that are not letters)
		BLT dec	;decrease the right pointer
		CMP R5, #0x7A	;it it is not A-Z and a-z
		BGT dec	;decrease the right pointer (Ignore all characters that are not letters)

					;check if they are equal
		CMP r4, r5	;compare them
		BEQ update	;if they are equal, update indices to check the next character pairs
		
				;if they are not equal
		B fail	;string is not a palindrome
		
inc		ADD r2, r2, #1	;increase the left pointer
		B palin			;go to palin to check updated char
		
dec		SUB r3, r3, #1	;decrease the right pointer 
		B palin			;go to palin to check updated char
		
update	ADD r2, r2, #1	;increase the left pointer
		SUB r3, r3, #1	;decrease the right pointer 
		B palin			;go to palin to check updated char
		
success	MOV r0, #1	;store success value in r0
		B loop		;finish!!
		
fail	MOV r0, #2	;store fail value in r0

loop	B loop	;infinite loop! the end!
		
	
STRING	DCB " aba"	;string 
EoS		DCB 0x00		;end of string
		END