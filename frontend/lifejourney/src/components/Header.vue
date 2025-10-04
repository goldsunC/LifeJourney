<template>
  <header id="navbar">
    <div class="container">
      <div class="flex justify-between items-center">
        <router-link to="/" class="logo">
          LIFE<span class="text-accent">JOURNEY</span>
        </router-link>
        
        <!-- 桌面端导航 -->
        <nav class="nav-links">
          <router-link to="/" class="nav-link">HOME</router-link>
          <router-link to="/timeline" class="nav-link">TIMELINE</router-link>
          <a href="#PHOTOALBUM" class="nav-link">PHOTOALBUM</a>
          <a href="#WISHLIST" class="nav-link">WISHLIST</a>
          <router-link to="/about" class="nav-link">ABOUT</router-link>
        </nav>
        
        <!-- 移动端菜单按钮 -->
        <button id="menu-toggle" class="menu-toggle">
          ☰
        </button>
      </div>
      
        <!-- 移动端导航菜单 -->
        <div id="mobile-menu">
          <router-link to="/" class="nav-link">HOME</router-link>
          <router-link to="/timeline" class="nav-link">TIMELINE</router-link>
          <a href="#PHOTOALBUM" class="nav-link">PHOTOALBUM</a>
          <a href="#WISHLIST" class="nav-link">WISHLIST</a>
          <router-link to="/about" class="nav-link">ABOUT</router-link>
        </div>
    </div>
  </header>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'

const isMenuOpen = ref(false)

onMounted(() => {
  // 移动端菜单切换
  const menuToggle = document.getElementById('menu-toggle')
  const mobileMenu = document.getElementById('mobile-menu')
  
  menuToggle.addEventListener('click', () => {
    isMenuOpen.value = !isMenuOpen.value
    mobileMenu.classList.toggle('active')
    menuToggle.innerHTML = isMenuOpen.value ? '✕' : '☰'
  })
  
  // 导航栏滚动效果
  const navbar = document.getElementById('navbar')
  
  const handleScroll = () => {
    if (window.scrollY > 50) {
      navbar.classList.add('nav-scrolled')
    } else {
      navbar.classList.remove('nav-scrolled')
    }
  }
  
  window.addEventListener('scroll', handleScroll)
  
  // 清理事件监听器
  return () => {
    window.removeEventListener('scroll', handleScroll)
  }
})
</script>

<style scoped>
/* 导航栏样式 */
#navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  padding: 1.5rem 0;
  z-index: 50;
  transition: background-color 0.3s ease, padding 0.3s ease;
  background-color: transparent;
}

#navbar.nav-scrolled {
  background-color: rgba(255, 255, 255, 0.95);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
  padding: 1rem 0;
}

.logo {
  font-size: 1.5rem;
  font-weight: 900;
  letter-spacing: -0.02em;
  color: white;
}

#navbar.nav-scrolled .logo {
  color: #121212;
}

.nav-links {
  display: flex;
  gap: 2.5rem;
}

.nav-link {
  text-transform: uppercase;
  letter-spacing: 0.25em;
  font-size: 0.875rem;
  font-weight: 500;
  color: white;
  text-decoration: none;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #FF5A5F;
}

#navbar.nav-scrolled .nav-link {
  color: #121212;
}

.menu-toggle {
  display: none;
  font-size: 1.5rem;
  background: none;
  border: none;
  cursor: pointer;
  color: white;
}

#navbar.nav-scrolled .menu-toggle {
  color: #121212;
}

#mobile-menu {
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  background-color: white;
  box-shadow: 0 4px 6px rgba(0,0,0,0.1);
  padding: 1.5rem 0;
  transform: translateY(-20px);
  opacity: 0;
  pointer-events: none;
  transition: all 0.3s ease;
}

#mobile-menu.active {
  transform: translateY(0);
  opacity: 1;
  pointer-events: auto;
}

#mobile-menu .nav-link {
  display: block;
  padding: 0.75rem 0;
  border-bottom: 1px solid #f0f0f0;
  text-transform: uppercase;
  letter-spacing: 0.25em;
  font-size: 0.875rem;
  color: #121212;
  text-decoration: none;
  transition: color 0.3s ease;
}

#mobile-menu .nav-link:hover {
  color: #FF5A5F;
}

#mobile-menu .nav-link:last-child {
  border-bottom: none;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .nav-links {
    display: none;
  }
  
  .menu-toggle {
    display: block;
  }
}

.container {
  width: 90%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

.flex {
  display: flex;
}

.justify-between {
  justify-content: space-between;
}

.items-center {
  align-items: center;
}

.text-accent {
  color: #FF5A5F; /* 与Home.vue保持一致的强调色 */
}
</style>
