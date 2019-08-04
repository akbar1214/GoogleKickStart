#include <bits/stdc++.h>

using namespace std;

string arr[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
string arr1[] =
    {"", "", "double", "triple", "quadruple", "quintuple", "sextuple", "septuple", "octuple", "nonuple", "decuple"};

void solve(const string &str) {

    int i, j;
    for (i = 0, j = i + 1; i < str.size();) {

        while (j < str.size() and str[j] == str[i]) ++j;
        int len = j - i;
        int digit = str[i] - '0';
        if (len == 1 || len > 10) {
            for (int ii = 0; ii < len; ii++) cout << arr[digit] << " ";
        } else {
            cout << arr1[len] << " " << arr[digit] << " ";
        }
        i = j;
    }
}

int main() {

    int T;
    cin >> T;
    for (int tt = 1; tt <= T; tt++) {

        string number, format;
        cin >> number >> format;
        for (char &x:format) {
            if (x == '-') x = ' ';
        }
        stringstream ss(format);
        vector<int> tokens;
        int temp;
        while (ss >> temp) {
            tokens.push_back(temp);
        }
        printf("Case #%d: ", tt);
        int total = 0;
        for (int i = 0; i < tokens.size(); i++) {
            solve(number.substr(total, tokens[i]));
            total += tokens[i];
        }
        cout << '\n';
    }
}
