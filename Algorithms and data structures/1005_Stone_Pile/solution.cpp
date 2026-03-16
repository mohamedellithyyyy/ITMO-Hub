#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
int getNumberOfStones() {
    int n;
    cin >> n;
    return n;
}
vector<int> getStoneWeights(int n) {
    vector<int> weights(n);
    for (int i = 0; i < n; ++i) {
        cin >> weights[i];
    }
    return weights;
}
int findMinimalDifference(const vector<int>& stones, int index = 0, int sumPile1 = 0, int sumPile2 = 0) {
    if (index == stones.size()) {
        return abs(sumPile1 - sumPile2);
    }
    int diff1 = findMinimalDifference(stones, index + 1, sumPile1 + stones[index], sumPile2);
    int diff2 = findMinimalDifference(stones, index + 1, sumPile1, sumPile2 + stones[index]);
    return min(diff1, diff2);
}

int main() {
    int n = getNumberOfStones();
    vector<int> stones = getStoneWeights(n);
    int minimalDifference = findMinimalDifference(stones);
    cout << minimalDifference << endl;
    return 0;
}