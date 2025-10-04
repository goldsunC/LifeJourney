<template>
  <div id="app">
    <!-- 头部组件 -->
    <Header />
    
    <!-- 主内容区域 -->
    <main>
      <Home />
    </main>
    
    <!-- 底部组件 -->
    <Footer />
  </div>
</template>

<script setup>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import Home from './views/Home.vue'
</script>

<style>
/* 全局样式重置 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html {
  scroll-behavior: smooth;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #121212;
  background-color: #F8F8F8;
  line-height: 1.6;
}

/* 确保主内容区域可以自动伸展 */
#app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

main {
  flex: 1;
}

/* 全局链接样式 */
a {
  color: inherit;
  text-decoration: none;
  transition: color 0.3s ease;
}

/* 确保Header组件在顶部固定且背景透明 */
header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  background-color: transparent;
}

/* 移除主内容区的顶部边距，让hero区域能够延伸到header下方 */
main {
  margin-top: 0;
}

/* 确保平滑滚动行为在所有浏览器上都有效 */
html {
  scroll-behavior: smooth !important;
}

/* 修复某些移动设备上的滚动问题 */
@media (max-width: 768px) {
  body {
    -webkit-overflow-scrolling: touch;
  }
}
</style>