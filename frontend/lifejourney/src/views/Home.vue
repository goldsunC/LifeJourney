<template>
  <div class="home">
    <!-- 英雄区 -->
    <section id="home">
      <div class="hero-bg">
        <img src="http://120.26.34.235:9000/lifejourney/2025/10/ab313ee3-8b1c-4aaa-8ae9-30b670ccdb98.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=kangning666%2F20251004%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20251004T181852Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=c256cd10823c99926e40d0b83f92a287d576a181a38680f53949433c3523a9af" alt="Elena Mori's artwork">
        <div class="hero-overlay"></div>
      </div>
      
      <div class="hero-content container">
        <h1 class="hero-title fade-in">
          Sharing <br>
          bits of your <span class="text-accent">LifeJourney</span>
        </h1>
        <p class="hero-subtitle max-w-2xl mx-auto fade-in">
          A personal life journey recording and showcasing platform
        </p>
        <div class="hero-buttons fade-in">
          <a href="#work" class="btn btn-primary">My LifeJourney</a>
          <router-link to="/about" class="btn btn-secondary">About Me</router-link>
        </div>
      </div>
      
      <div class="absolute bottom-10 left-half transform-center translate-x-negative-half animate-bounce fade-in">
        <a href="#about" aria-label="Scroll down">
          <span style="font-size: 2rem;">▼</span>
        </a>
      </div>
    </section>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { onMounted } from 'vue'

// 添加滚动动画效果
const addScrollAnimation = () => {
  const fadeElements = document.querySelectorAll('.fade-in');
  
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.style.opacity = '1';
        entry.target.style.transform = 'translateY(0)';
      }
    });
  }, {
    threshold: 0.1
  });
  
  fadeElements.forEach(el => {
    observer.observe(el);
  });
}

onMounted(() => {
  // 添加动画效果
  addScrollAnimation();
});
</script>

<style scoped>
/* 英雄区样式 */
#home {
  position: relative;
  min-height: 100vh;
  overflow: hidden;
  margin-top: 0;
  padding-top: 0;
  z-index: 1;
}

.hero-bg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
}

.hero-bg img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: top center;
}

.hero-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.hero-content {
  position: relative;
  text-align: center;
  color: white;
  padding: 2rem;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.hero-title {
  font-size: 4rem;
  font-weight: 800;
  line-height: 1.2;
  margin-bottom: 1.5rem;
}

.text-accent {
  color: #FF5A5F;
}

.hero-subtitle {
  font-size: 1.25rem;
  margin-bottom: 2.5rem;
}

.hero-buttons {
  display: flex;
  justify-content: center;
  gap: 1.5rem;
  flex-wrap: wrap;
}

.btn {
  padding: 1rem 2rem;
  border-radius: 4px;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.3s ease;
  cursor: pointer;
  display: inline-block;
}

.btn-primary {
  background-color: #FF5A5F;
  color: white;
  border: 2px solid #FF5A5F;
}

.btn-primary:hover {
  background-color: transparent;
  color: #FF5A5F;
}

.btn-secondary {
  background-color: transparent;
  color: white;
  border: 2px solid white;
}

.btn-secondary:hover {
  background-color: white;
  color: #121212;
}

.animate-bounce {
  animation: bounce 2s infinite;
}

@keyframes bounce {
  0%, 20%, 50%, 80%, 100% {
    transform: translateY(0);
  }
  40% {
    transform: translateY(-20px);
  }
  60% {
    transform: translateY(-10px);
  }
}

/* 淡入动画类 */
.fade-in {
  opacity: 0;
  transform: translateY(20px);
  transition: opacity 0.8s ease-out, transform 0.8s ease-out;
}

/* 为不同元素设置不同的动画延迟 */
.hero-title {
  transition-delay: 0.2s;
}

.hero-subtitle {
  transition-delay: 0.4s;
}

.hero-buttons {
  transition-delay: 0.6s;
}

.animate-bounce {
  transition-delay: 0.8s;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .hero-title {
    font-size: 3rem;
  }
  
  .hero-subtitle {
    font-size: 1rem;
  }
  
  .hero-buttons {
    flex-direction: column;
    align-items: center;
  }
  
  .btn {
    width: 100%;
    max-width: 250px;
  }
}
</style>
