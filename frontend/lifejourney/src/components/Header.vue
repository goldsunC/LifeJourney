<template>
  <header class="header">
    <nav class="nav">
      <div class="container">
        <div class="nav-content">
          <div class="logo">
            <router-link to="/" class="logo-link">
              <div class="logo-icon">📖</div>
              <span class="logo-text">LifeJourney</span>
            </router-link>
          </div>
          
          <div class="nav-menu" :class="{ active: isMenuOpen }">
            <router-link to="/" class="nav-link" @click="closeMenu">首页</router-link>
            <router-link to="/timeline" class="nav-link" @click="closeMenu">时间线</router-link>
            <router-link to="/articles" class="nav-link" @click="closeMenu">文章</router-link>
            <router-link to="/gallery" class="nav-link" @click="closeMenu">相册</router-link>
            <router-link to="/goals" class="nav-link" @click="closeMenu">愿望清单</router-link>
          </div>
          
          <div class="nav-actions">
            <button class="btn btn-secondary" @click="toggleTheme">
              {{ isDark ? '☀️' : '🌙' }}
            </button>
            <button class="menu-toggle" @click="toggleMenu" :class="{ active: isMenuOpen }">
              <span></span>
              <span></span>
              <span></span>
            </button>
          </div>
        </div>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { ref } from 'vue'

const isMenuOpen = ref(false)
const isDark = ref(false)

const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value
}

const closeMenu = () => {
  isMenuOpen.value = false
}

const toggleTheme = () => {
  isDark.value = !isDark.value
  // 这里可以添加主题切换逻辑
}
</script>

<style scoped>
.header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}

.nav {
  padding: 0;
}

.nav-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 70px;
}

.logo-link {
  display: flex;
  align-items: center;
  gap: 12px;
  text-decoration: none;
  color: #333;
  font-weight: 700;
  font-size: 1.5rem;
}

.logo-icon {
  font-size: 2rem;
}

.logo-text {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.nav-menu {
  display: flex;
  align-items: center;
  gap: 32px;
}

.nav-link {
  text-decoration: none;
  color: #666;
  font-weight: 500;
  transition: all 0.3s ease;
  position: relative;
}

.nav-link:hover,
.nav-link.router-link-active {
  color: #667eea;
}

.nav-link.router-link-active::after {
  content: '';
  position: absolute;
  bottom: -8px;
  left: 0;
  right: 0;
  height: 2px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 1px;
}

.nav-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.menu-toggle {
  display: none;
  flex-direction: column;
  justify-content: space-around;
  width: 24px;
  height: 24px;
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 0;
}

.menu-toggle span {
  width: 100%;
  height: 2px;
  background: #333;
  transition: all 0.3s ease;
  transform-origin: center;
}

.menu-toggle.active span:nth-child(1) {
  transform: rotate(45deg) translate(5px, 5px);
}

.menu-toggle.active span:nth-child(2) {
  opacity: 0;
}

.menu-toggle.active span:nth-child(3) {
  transform: rotate(-45deg) translate(7px, -6px);
}

@media (max-width: 768px) {
  .nav-menu {
    position: fixed;
    top: 70px;
    left: 0;
    right: 0;
    background: white;
    flex-direction: column;
    padding: 20px;
    gap: 20px;
    transform: translateY(-100%);
    opacity: 0;
    visibility: hidden;
    transition: all 0.3s ease;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  }
  
  .nav-menu.active {
    transform: translateY(0);
    opacity: 1;
    visibility: visible;
  }
  
  .menu-toggle {
    display: flex;
  }
  
  .nav-actions .btn {
    padding: 8px 12px;
    font-size: 14px;
  }
}
</style>
