import random

# Define the opcodes and their associated operations
opcodes = {
    0: lambda a, b: a + b & 0xFFFFFFFF,
    2: lambda a, b: a - b & 0xFFFFFFFF,
    4: lambda a, b: a & b,
    5: lambda a, b: a | b,
    6: lambda a, b: a ^ b,
    7: lambda a, b: ~(a | b) & 0xFFFFFFFF,
    10: lambda a, b: 1 if a > b else 0
}
# Function to format a 32-bit number as 8 groups of 2 hex digits
def format_hex(n):
    return '_'.join(f"{n:08X}"[i:i+2] for i in range(0, 8, 2))

# Generate 100 test vectors
for _ in range(100):
    # Generate random opcode, A and B
    opcode = random.choice(list(opcodes.keys()))
    a = random.randint(0, 0xFFFFFFFF)
    b = random.randint(0, 0xFFFFFFFF)

    # Calculate the result
    result = opcodes[opcode](a, b)

    # Print the test vector in the required format
    print(f"{opcode:X}_______{format_hex(a)}___{format_hex(b)}___{format_hex(result)}")