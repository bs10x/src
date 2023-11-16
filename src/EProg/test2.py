# Define the function psi
psi = {1: 1, 2: 3, 4: 9, 7: 5, 8: 11, 11: 7, 13: 13, 14: 15}

# Define the set of elements in Z_15*
Z_15_star = set(psi.keys())

# Generate all pairings
for a in Z_15_star:
    for b in Z_15_star:
        # Calculate a_odot_15_b and psi(a)_odot_16_psi(b)
        a_odot_15_b = (a * b) % 15
        psi_a_odot_16_psi_b = (psi[a] * psi[b]) % 16

        # Check if the property holds
        if psi[a_odot_15_b] == psi_a_odot_16_psi_b:
            print(f"Pair ({a}, {b}): \\psi({a} \\odot_{{15}}{b})= {psi[a_odot_15_b]} = \\psi({a}) \\odot_{{16}} \\psi({b})")
        else:
            print(f"Pair ({a}, {b}): Property does not hold")
