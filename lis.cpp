#include<bits/stdc++.h>
using namespace std;
#define int long long
const int eps = 1e-9;

void solve(){
    int n;
    cin >> n;
    vector<int> a(n+1,0),length(n+1,1);

    map<int,int>parent;
    for(int i = 1;i<=n;i++)cin >> a[i];
    parent[0] = -1;
    int ans = INT_MIN;
    for(int i = 1;i<=n;i++){
    	for(int j = 0;j<i;j++){
           if(a[i]>a[j]){
             if(1+length[j]>length[i]){
             	length[i] = 1+length[j];
             	parent[a[i]] = j;
             	ans = max(ans,length[i]);
             }

           }
    	}
    }
    //for(int i = 0;i<=n;i++)cout << length[i] << " ";
    cout << ans << '\n';

    
}

int32_t main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int t=1;
   // cin >> t;
    while(t--){
        solve();
        cout << '\n';
    }
}