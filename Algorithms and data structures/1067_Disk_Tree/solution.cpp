#include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <map>
#include <algorithm>

using namespace std;
map<string, vector<string>> global_map;
vector<string> splitPath(const string& path) {
    vector<string> result;
    string temp;
    stringstream ss(path);
    while (getline(ss, temp, '\\')) {
        result.push_back(temp);
    }
    return result;
}
void addPathToMap(const vector<string>& folders) {
    for (size_t i = 0; i < folders.size(); ++i) {
        string parent = (i == 0) ? "" : folders[i-1];
        string current = folders[i];
        auto& children = global_map[parent];
        if (find(children.begin(), children.end(), current) == children.end()) {
            children.push_back(current);
        }
    }
}
void printFolders(const string& parent, string indent = "") {
    if (global_map.find(parent) == global_map.end()) return;
    vector<string> children = global_map[parent];
    sort(children.begin(), children.end());
    for (const string& child : children) {
        cout << indent << child << endl;
        printFolders(child, indent + "  ");
    }
}

int main() {
    int n;
    cin >> n;
    cin.ignore();
    for (int i = 0; i < n; ++i) {
        string path;
        getline(cin, path);
        vector<string> folders = splitPath(path);
        addPathToMap(folders);
    }

    printFolders("");
    return 0;
}