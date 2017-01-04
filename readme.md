###第一次提交 FragmentTabHost初步使用
    下边tab导航的切换逻辑
     1.因每次切换销毁的都是View,数据还在，所以onCreateView会重复调用，因此在BaseViewPagerFragment类中，onCreateView方法中加上非空判断，防止重复加载。
###第二次提交 增加tablayout和toolbar