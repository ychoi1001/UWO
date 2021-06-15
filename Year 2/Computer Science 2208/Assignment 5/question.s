		AREA asgn5, CODE, READONLY
        ENTRY	;entry point for the application
		
		ADR R1, STRING1	;load STRING1 address to R1
		ADR R2, STRING2	;load STRING2 address to R2
		
loop	ldrb r0, [r1], #1	;load a char, and r1 points to the next char
		cmp r0, #'t'	;let's check if r0 is 't'
		beq check	;if it is 't', go to check!

store	strbne r0, [r2], #1	;if not, store it into [r2], and r2 points to the next position
		cmp r0, #0x00	;let's check if it is the end of string
		bne loop	;loop until it reaches to the end of string
		beq done	;finish the program if it reaches to the end of string
 
check	ldrb r3, [r1, #-2]	;to check if it is the first character of string1
		cmp r3, #0xfe	;compare with 0xfe (which is last 2 bits of done b done)
		cmpne r3, #' '	;if it is not the first character of string1, let's check if it is a space
		bne store	;if it is not a space, store it
		
		ldrb r3, [r1]	;let's check if 'h' is followed after 't'
		cmp r3, #'h'	;compare with 'h'
		bne store	;if it is not 'h', and then store
		
		ldrb r3, [r1, #1] ;let's check if 'e' is followed after 'th'
		cmp r3, #'e'	;compare with 'e'
		bne store	;if it is not 'e', and then store
		
		ldrb r3, [r1, #2]	;let's check if ' ' is followed after 'the'
		cmp r3, #' '	;compare with ' '		
		cmpne r3, #0x00	;if it is not ' ', let's check if it is the end of string
		bne store ;if not, store it
		addeq r1, #2	;if it is a space or the end of string, add 2 to r1 (to skip 'the')
		b loop ;go back to loop
		
done	b done	;infinite loop! the end!
		

STRING1	DCB "and the man said they must go"	;String1
EoS		DCB 0x00	;end of string1
STRING2	space 0x7F	;just allocating 127 bytes
		END