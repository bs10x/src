import random

# Define the opcodes and their associated operations
opcodes = {
    0: lambda a, b: a + b,
    2: lambda a, b: a - b,
    4: lambda a, b: a & b,
    5: lambda a, b: a | b,
    6: lambda a, b: a ^ b,
    7: lambda a, b: ~(a | b) & 0xFFFFFFFF,
    10: lambda a, b: 1 if a < b else 0
}

# Generate 100 test vectors
for _ in range(100):
    # Generate random opcode, A and B
    opcode = random.choice(list(opcodes.keys()))
    a = random.randint(0, 0xFFFFFFFF)
    b = random.randint(0, 0xFFFFFFFF)

    # Calculate the result
    result = opcodes[opcode](a, b)

    # Print the test vector in the required format
    print(f"{opcode:08X}_{a:08X}_{b:08X}_{result:08X}")