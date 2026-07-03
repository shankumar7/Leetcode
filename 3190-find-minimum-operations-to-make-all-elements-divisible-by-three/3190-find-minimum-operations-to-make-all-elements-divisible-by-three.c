int minimumOperations(int* nums, int numsSize) {
    int c=0;
        for(int i=0;i<numsSize;i++){
            if (nums[i]%3!=0) c++;
        }
        return c;
}