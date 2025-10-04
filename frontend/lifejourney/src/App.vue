<template>
  <div id="app">
    <Header @theme-change="handleThemeChange" />
    <router-view class="main-content" />
    <Footer />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'

// 定义主题状态
const isDark = ref(false)

// 从localStorage加载主题状态（如果有）
onMounted(() => {
  const savedTheme = localStorage.getItem('theme')
  if (savedTheme) {
    isDark.value = savedTheme === 'dark'
    updateTheme()
  }
})

// 更新主题样式
const updateTheme = () => {
  if (isDark.value) {
    document.body.classList.add('dark-theme')
    localStorage.setItem('theme', 'dark')
  } else {
    document.body.classList.remove('dark-theme')
    localStorage.setItem('theme', 'light')
  }
}

// 处理主题变更事件
const handleThemeChange = (themeState) => {
  isDark.value = themeState
  updateTheme()
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
    'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
    sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  line-height: 1.6;
  color: #333;
  background-color: #fafafa;
  transition: background-color 0.3s ease, color 0.3s ease;
}

#app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.main-content {
  flex: 1;
  margin-top: 85px; /* 与Header高度一致 */
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

.btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 12px 24px;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: 500;
  text-decoration: none;
  cursor: pointer;
  transition: all 0.3s ease;
  gap: 8px;
}

.btn-primary {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

.btn-primary:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 25px rgba(102, 126, 234, 0.3);
}

.btn-secondary {
  background: white;
  color: #667eea;
  border: 2px solid #667eea;
}

.btn-secondary:hover {
  background: #667eea;
  color: white;
  transform: translateY(-2px);
}

.card {
  background: white;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
  overflow: hidden;
}

.card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 30px rgba(0, 0, 0, 0.12);
}

.section {
  padding: 80px 0;
}

.section-title {
  font-size: 2.5rem;
  font-weight: 700;
  text-align: center;
  margin-bottom: 60px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.grid {
  display: grid;
  gap: 30px;
}

.grid-2 {
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
}

.grid-3 {
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
}

.grid-4 {
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
}

.fade-in {
  opacity: 0;
  transform: translateY(30px);
  animation: fadeInUp 0.8s ease forwards;
}

@keyframes fadeInUp {
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@media (max-width: 768px) {
  .section {
    padding: 60px 0;
  }
  
  .section-title {
    font-size: 2rem;
    margin-bottom: 40px;
  }
  
  .container {
    padding: 0 15px;
  }
}
/* 深色主题样式 */
body.dark-theme {
  background-color: #000 !important;
  color: #fff !important;
}

body.dark-theme #app {
  background-color: #000 !important;
  color: #fff;
}

body.dark-theme .main-content {
  background-color: #000 !important;
}

/* 深色主题下的卡片样式调整 */
body.dark-theme .card {
  background: #1a1a1a !important;
  color: #fff !important;
  box-shadow: 0 4px 20px rgba(255, 255, 255, 0.05);
}

/* 深色主题下的按钮样式调整 */
body.dark-theme .btn-secondary {
  background: #1a1a1a !important;
  color: #667eea !important;
  border: 2px solid #667eea;
}

/* 确保深色主题样式应用到所有内容区域 */
body.dark-theme *:not(.footer):not(.header) {
  background-color: inherit !important;
  color: inherit !important;
}

/* 深色主题下的卡片样式调整 */
#app.dark-theme .card {
  background: #1a1a1a;
  color: #fff;
  box-shadow: 0 4px 20px rgba(255, 255, 255, 0.05);
}

#app.dark-theme .section-title {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

/* 深色主题下的按钮样式调整 */
#app.dark-theme .btn-primary {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

#app.dark-theme .btn-secondary {
  background: #1a1a1a;
  color: #667eea;
  border: 2px solid #667eea;
}

/* 深色主题下的导航链接样式调整 */
#app.dark-theme .nav-link {
  color: rgba(255, 255, 255, 0.85);
}

#app.dark-theme .nav-link:hover,
#app.dark-theme .nav-link.router-link-active {
  color: white;
}
</style>