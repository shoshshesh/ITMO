#include <stdio.h>
#include <stdlib.h>

int howManySolutions(const float* matrix, int n);
void swap(float* matrix, int line1, int line2, int n, _Bool *flag);
int main(int argc, char** argv) {
    if (argc != 3) {
        printf("Wrong number of arguments\nActual: %i\nExpected: 3\nExample: lab1.exe in.txt out.txt\n", argc);
        return 1;
    }
    FILE* in = fopen(argv[1], "r");
    if (in == NULL) {
        printf("Cannot open an input file");
        return 1;
    }
    int n;
    fscanf(in, "%i", &n);
    float* matrix = (float*)malloc(n * (n + 1) * sizeof(float));
    if (matrix == NULL) {
        printf("Cannot allocate memory");
        fclose(in);
        return 2;
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n + 1; j++) {
            fscanf(in, "%f", &matrix[i * (n + 1) + j]);
        }
    }
    fclose(in);
    for (int i = 0; i < n - 1; i++) {
        if (matrix[i * (n + 1) + i] == 0) {
            int temp = i;
            do {
                temp++;
            } while (temp < n && matrix[temp * (n + 1) + i] == 0);
            if (temp == n) {
                continue;
            }
            _Bool *flag = 0;
            swap(matrix, i, temp, n, flag);
            if (flag) {
                free(matrix);
                return 2;
            }
        }
        for (int j = i + 1; j < n; j++) {
            if (matrix[j * (n + 1) + i] != 0) {
                float factor = -(matrix[j * (n + 1) + i] / matrix[i * (n + 1) + i]);
                for (int k = 0; k < n + 1; k++) {
                    matrix[j * (n + 1) + k] = factor * matrix[i * (n + 1) + k] + matrix[j * (n + 1) + k];
                }
            }
        }
    }
    int solutions = howManySolutions(matrix, n);
    FILE* out = fopen(argv[2], "w");
    if (out == NULL) {
        printf("Cannot find and create an output file");
        free(matrix);
        return 1;
    }
    if (solutions == 0) {
        fprintf(out, "no solution\n");
    } else if (solutions == 2) {
        fprintf(out, "many solutions\n");
    } else {
        float* result = (float*)malloc(n * sizeof(float));
        if (result == NULL) {
            printf("Cannot allocate memory");
            free(matrix);
            fclose(out);
            return 2;
        }
        _Bool flag = 1;
        for (int i = n - 1; i >= 0; i--) {
            float dividend = matrix[i * (n + 1) + n];
            for (int j = n - 1; j > i; j--) {
                dividend -= matrix[i * (n + 1) + j] * result[j];
            }
            if (matrix[i * (n + 1) + i] != 0) {
                result[i] = dividend / matrix[i * (n + 1) + i];
            } else {
                fprintf(out,"many solutions\n");
                flag = 0;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < n; i++) {
                fprintf(out,"%g\n", result[i]);
            }
        }
        free(result);
    }
    free(matrix);
    fclose(out);
    return 0;
}

void swap(float* matrix, const int line1, const int line2, const int n, _Bool *flag) {
    float *tempLine = (float*) malloc((n + 1) * sizeof(float));
    if (tempLine == NULL) {
        printf("Cannot allocate memory");
        *flag = 1;
        return;
    }
    for (int i = 0; i < n + 1; i++) {
        tempLine[i] = matrix[line1 * (n + 1) + i];
    }
    for (int i = 0; i < n + 1; i++) {
        matrix[line1 * (n + 1) + i] = matrix[line2 * (n + 1) + i];
    }
    for (int i = 0; i < n + 1; i++) {
        matrix[line2 * (n + 1) + i] = tempLine[i];
    }
    free(tempLine);
}

int howManySolutions(const float* matrix, const int n) {
    for (int i = 0; i < n; i++) {
        if (matrix[i * (n + 1) + i] == 0) {
            int zeros = 0;
            for (int j = i + 1; j < n; j++) {
                if (matrix[i * (n + 1) + j] == 0) {
                    zeros++;
                }
            }
            if (zeros == n - (i + 1)) {
                if (matrix[i * (n + 1) + n] == 0) {
                    return 2;
                } else {
                    return 0;
                }
            }
        }
    }
    return 1;
}