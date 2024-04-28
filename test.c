// gcc -o hacked hacked.c

#include <windows.h>

int main() {
    MessageBox(NULL, "You have been hacked!", "", MB_ICONINFORMATION | MB_OK);
    return 0;
}
