int mirrorDistance(int n) {
    int r=0,o=n;
    while(o!=0){
        r=(r*10)+(o%10);
        o/=10;
    }
    int re=n-r;
    return (re<0)?(-1*re):re;
}