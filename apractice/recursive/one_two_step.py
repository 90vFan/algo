class Solution:
    def __init__(self):
        self.cache = {}

    def climbStairs(self, n: int) -> int:
        if (n == 0):
            self.cache[0] = 0
        if (n == 1):
            self.cache[1] = 1
        if (n == 2):
            self.cache[2] = 2

        return self.climb(n)

    def climb(self, n: int) -> int:
        if (self.cache.get(n, None)):
            return self.cache[n]

        self.cache[n] = self.climb(n - 1) + self.climb(n - 2)

        return self.cache[n]


if __name__ == '__main__':
    sol = Solution()
    ret = sol.climbStairs(1)
    print(f'1 {ret}')
    ret = sol.climbStairs(2)
    print(f'2 {ret}')
    ret = sol.climbStairs(3)
    print(f'3 {ret}')
    ret = sol.climbStairs(4)
    print(f'4 {ret}')
    ret = sol.climbStairs(1001)
    print(f'1001 {ret}')
