# 2023项目实训项目——电源产销存一体化系统

**本项目使用java17版本和MySQL8版本**

## 版本控制（git）的使用方法：

1. 打开一个空的文件夹，win11：右键 > 显示更多选项 > Git Bash Here。win10：右键 > Git Bash Here
2. 将该仓库复制到本地使用`git clone https://github.com/mizulk/power-sell-system.git`
3. 使用idea打开file > open选择克隆下来的文件夹（power-sell-system）
4. 写完一个功能后保存`git add .`加入暂存区，再使用`git commit -m "对该功能的描述"`来提交
5. 使用`git push orgin main`来将代码推送到远程仓库
6. 每次打开idea用`git pull`来更新代码（别人写的）

## 创建页面的原则

### 命名

在view包下面新建类，类名为对应的功能+View，例如：UserView表示为用户页面，还可再细分为：UserMainView表示为用户主页面、 UserChargeView用户充值页面  
起完名字后在router包下的PanelName枚举类里添加上你创建的类名，例如UserMainView.java在PanelName下写成USER_MAIN_VIEW("userMain",UserMainView.class)  
每个枚举之间用逗号隔开，最后一个枚举需要用分号结束。每个枚举类要建到**LOGIN_VIEW**下面。


枚举类的作用是为router包下的Router使用的，在App类下有`Router userRouter();`方法来获取Router实例，其中可以转跳到想要的页面的功能。  
`Router::showPanel`用于页面跳转指定页面，`Router::showPreviousPanel`用于跳转到前一个页面

### 其他

在view包下的所有类都要继承BasicView，实现两个抽象方法。抽象方法的详细可以查看BasicView类。  
`BasicView::buildLayout`里面写有关于页面布局的代码。`BasicView::addListener`里面写有关于事件监听的代码  
具体可以参考`SelectLoginView`类的代码。

