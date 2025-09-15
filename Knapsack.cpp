#include<bits/stdc++.h>
using namespace std;
#define int long long
const int eps = 1e-9;
int dp[100][100],k;
vector<int>wt,v;
int weight(int n,int x){
	if(n == 0){
		//cout << n << " " << x << '\n';
        if(wt[n]<=x) return v[n];
		return 0;
	}
	if(dp[n][x]!=-1)return dp[n][x];
	int parbena = weight(n-1,x);
	int parbe = INT_MIN;
	if(wt[n]<=x){
		parbe = v[n] + weight(n-1,x-wt[n]);
	}
	return dp[n][x] = max(parbe,parbena);
	
}
void solve(){
    int n,high;
    cin >> n >> high;
    wt = vector<int>(n+1);
    v = vector<int>(n+1);
    for(int i = 0;i<n;i++)cin >> wt[i];
    for(int i = 0;i<n;i++)cin >> v[i];
    memset(dp,-1,sizeof(dp));
    cout << weight(n-1,high) << '\n';
   //cout << dp[0][high] << " ";
  
}

int32_t main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int t = 1;
    //cin >> t;
    while(t--){
        solve();
        cout << '\n';
    }
}