class Solution {
    class Node {
        int l,r,prod;
        int[] cnt;
        Node(int l,int r,int k) {
            this.l=l;
            this.r=r;
            this.prod=1;
            this.cnt=new int[k];
        }
    }
    class SegmentTree {
        int k;
        Node[] tree;
        SegmentTree(int[] nums,int k) {
            this.k=k;
            tree=new Node[4*nums.length];
            build(1,0,nums.length-1,nums);
        }
        void build(int node,int l,int r,int[] nums) {
            tree[node]=new Node(l,r,k);
            if(l==r) {
                int v=nums[l]%k;
                tree[node].prod=v;
                tree[node].cnt[v]=1;
                return;
            }
            int mid=(l+r)/2;
            build(node*2,l,mid,nums);
            build(node*2+1,mid+1,r,nums);
            merge(node);
        }
        void merge(int node) {
            Node a=tree[node*2];
            Node b=tree[node*2+1];
            tree[node].prod=(a.prod*b.prod)%k;
            for(int i=0;i<k;i++)
                tree[node].cnt[i]=a.cnt[i];
            for(int i=0;i<k;i++)
                tree[node].cnt[(a.prod*i)%k]+=b.cnt[i];
        }
        void update(int node,int l,int r,int idx,int val) {
            if(l==r) {
                val%=k;
                Arrays.fill(tree[node].cnt,0);
                tree[node].prod=val;
                tree[node].cnt[val]=1;
                return;
            }
            int mid=(l+r)/2;
            if(idx<=mid)
                update(node*2,l,mid,idx,val);
            else
                update(node*2+1,mid+1,r,idx,val);
            merge(node);
        }
        Node query(int node,int l,int r,int ql,int qr) {
            if(ql<=l&&r<=qr)
                return tree[node];
            int mid=(l+r)/2;
            if(qr<=mid)
                return query(node*2,l,mid,ql,qr);
            if(ql>mid)
                return query(node*2+1,mid+1,r,ql,qr);
            return mergeNodes(query(node*2,l,mid,ql,qr),query(node*2+1,mid+1,r,ql,qr));
        }
        Node mergeNodes(Node a,Node b) {
            Node res=new Node(0,0,k);
            res.prod=(a.prod*b.prod)%k;
            for(int i=0;i<k;i++)
                res.cnt[i]=a.cnt[i];
            for(int i=0;i<k;i++)
                res.cnt[(a.prod*i)%k]+=b.cnt[i];
            return res;
        }
    }
    public int[] resultArray(int[] nums,int k,int[][] queries) {
        int n=nums.length;
        SegmentTree tree=new SegmentTree(nums,k);
        int[] ans=new int[queries.length];
        for(int i=0;i<queries.length;i++) {
            int index=queries[i][0];
            int value=queries[i][1];
            int start=queries[i][2];
            int x=queries[i][3];
            tree.update(1,0,n-1,index,value);
            Node res=tree.query(1,0,n-1,start,n-1);
            ans[i]=res.cnt[x];
        }
        return ans;
    }
}