#include <stdlib.h>
#include <stdio.h>


float invsqrt(float x) {
    long i;
    float x2, y;
    const float threehalfs = 1.5F;

    x2 = x * 0.5F;
    y = x;
    i = *(long *)&y;          // cast bits of float y into long i
    i = 0x5f3759df - (i >> 1); // mu (scaling factor) minus half of i
    y = *(float *)&i;          // cast bits of long i into float y
    y = y * (threehalfs - (x2 * y * y));    // Newton-Raphson iteration
    // y = y * (threehalfs - (x2 * y * y));    // Newton-Raphson iteration

    return y;
}





int main() {
    char a = 'a';
    char abc[3] = {'a', 'b', 'c'};
    char hello[] = "Hello, World!";

    int n = 100;
    for (int i = 0; i < n; i++) {
        // printf("i=%i\t@\t%p\n", i, &i);
        // printf("invsqrt(%i)=%f\n", i, invsqrt(i));
    }
    
    printf("invsqrt(100)=%f", invsqrt(100.0F));

    return 0;
}

