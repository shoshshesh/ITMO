#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char surname[21];
    char name[21];
    char patronymic[21];
    long long int number;
} Person;

void swap(Person *a, Person *b);
int compare(Person first, Person second);
void quickSort(Person *array, int l, int r);
int med(int l, int m, int r, Person *array);


int main(int argc, char **argv) {
    if (argc != 3) {
        printf("Wrong number of arguments\nActual: %i\nExpected: 3\nExample: lab3.exe in.txt out.txt\n", argc);
        return 1;
    }
    FILE *in = fopen(argv[1], "r");
    if (in == NULL) {
        printf("Cannot open the input file");
        return 1;
    }
    unsigned int size = 3;
    Person *people = malloc(size * sizeof(Person));
    if (people == NULL) {
        printf("Cannot allocate memory");
        fclose(in);
        return 2;
    }
    int i = 0;
    while (!feof(in)) {
        if (i == size) {
            double factor = 2;
            Person *temp = NULL;
            unsigned int tempSize = size;
            while (temp == NULL && factor > 1) {
                tempSize = (unsigned int) (size * factor);
                temp = realloc(people, tempSize * sizeof(Person));
                factor -= 0.05;
            }
            if (temp == NULL) {
                tempSize = size + 10;
                temp = realloc(people, tempSize * sizeof(Person));
            }
            if (temp == NULL) {
                printf("Cannot allocate memory");
                free(people);
                fclose(in);
                return 2;
            } else {
                people = temp;
                temp = NULL;
                size = tempSize;
            }
        }
        fscanf(in, "%s %s %s %lli", people[i].surname, people[i].name, people[i].patronymic, &people[i].number);
        i++;
    }
    fclose(in);
    quickSort(people, 0, i - 1);
    FILE *out = fopen(argv[2], "w");
    if (out == NULL) {
        printf("Cannot find and create an output file");
        free(people);
        return 1;
    }
    for (int j = 0; j < i; j++) {
        fprintf(out, "%s %s %s %lli\n", people[j].surname, people[j].name, people[j].patronymic, people[j].number);
    }
    fclose(out);
    free(people);
    return 0;
}

void swap(Person *a, Person *b) {
    Person temp = *a;
    *a = *b;
    *b = temp;
}

int compare(Person first, Person second) {
    if (strcmp(first.surname, second.surname) > 0) {
        return 1;
    } else if (strcmp(first.surname, second.surname) < 0) {
        return -1;
    } else if (strcmp(first.name, second.name) > 0) {
        return 1;
    } else if (strcmp(first.name, second.name) < 0) {
        return -1;
    } else if (strcmp(first.patronymic, second.patronymic) > 0) {
        return 1;
    } else if (strcmp(first.patronymic, second.patronymic) < 0) {
        return -1;
    } else if (first.number > second.number) {
        return 1;
    } else if (first.number < second.number) {
        return -1;
    } else {
        return 0;
    }
}

int med(const int l, const int m, const int r, Person *array) {
    if (compare(array[l], array[m]) == -1) {
        if (compare(array[l], array[r]) >= 0) {
            return l;
        } else if (compare(array[m], array[r]) == -1) {
            return m;
        }
    } else {
        if (compare(array[l], array[r]) == -1) {
            return l;
        } else if (compare(array[m], array[r]) >= 0) {
            return m;
        }
    }
    return r;
}

void quickSort(Person *array, const int l, const int r) {
    if (r <= l) {
        return;
    }
    int median = med(l, (l+r)/2, r, array);
    swap(&array[median], &array[r]);
    Person v = array[r];
    if (r <= l) {
        return;
    }
    int i = l;
    int j = r - 1;
    int p = l - 1;
    int q = r;

    while (i <= j) {
        while (compare(array[i], v) < 0) {
            i++;
        }
        while (compare(array[j], v) > 0) {
            j--;
        }
        if (i >= j) {
            break;
        }
        swap(&array[i], &array[j]);
        if (compare(array[i], v) == 0) {
            p++;
            swap(&array[p], &array[i]);
        }
        i++;
        if (compare(array[j], v) == 0) {
            q--;
            swap(&array[q], &array[j]);
        }
        j--;
    }
    swap(&array[i], &array[r]);
    j = i - 1;
    i++;
    for (int k = l; k <= p; k++, j--) {
        swap(&array[k], &array[j]);
    }
    for (int k = r - 1; k >= q; k--, i++) {
        swap(&array[k], &array[i]);
    }
    quickSort(array, l, j);
    quickSort(array, i, r);
}