<template>
  <div class="photoalbum-container">
    <header class="page-header">
      <h1 class="page-title fade-in">我的相册</h1>
      <p class="page-subtitle fade-in">记录生活中的美好瞬间</p>
    </header>

    <main class="photoalbum-content">
      <div class="photo-gallery">
        <div 
          v-for="(photo, index) in photos" 
          :key="index" 
          class="photo-item fade-in"
          @click="openPhoto(index)"
        >
          <img 
            :src="photo.url" 
            :alt="photo.title"
            class="photo-image"
            loading="lazy"
          >
          <div class="photo-overlay">
            <h3 class="photo-title">{{ photo.title }}</h3>
            <p class="photo-date">{{ photo.date }}</p>
          </div>
        </div>
      </div>
    </main>

    <!-- 图片查看器模态框 -->
    <div v-if="selectedPhoto !== null" class="modal" @click="closePhoto">
      <div class="modal-content" @click.stop>
        <span class="close-btn" @click="closePhoto">&times;</span>
        <img :src="photos[selectedPhoto].url" :alt="photos[selectedPhoto].title" class="modal-image">
        <div class="modal-info">
          <h2>{{ photos[selectedPhoto].title }}</h2>
          <p>{{ photos[selectedPhoto].date }}</p>
          <p class="photo-description">{{ photos[selectedPhoto].description }}</p>
        </div>
      </div>
    </div>

    <!-- 返回顶部按钮 -->
    <button 
      v-if="showBackToTop"
      class="back-to-top"
      @click="scrollToTop"
      aria-label="返回顶部"
    >
      <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M12 19V5M12 5L5 12M12 5L19 12" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
      </svg>
    </button>
  </div>
</template>

<script>
import { ref, onMounted, onUnmounted } from 'vue'

export default {
  name: 'Photoalbum',
  setup() {
    const selectedPhoto = ref(null)
    const showBackToTop = ref(false)
    
    // 相册数据
    const photos = ref([
      {
        url: 'https://picsum.photos/id/10/800/600',
        title: '山水之间',
        date: '2023-05-15',
        description: '在山间小溪旁拍摄的风景照，阳光透过树叶洒在水面上，形成斑驳的光影。'
      },
      {
        url: 'https://picsum.photos/id/20/800/600',
        title: '城市夜景',
        date: '2023-07-22',
        description: '城市的夜晚灯火辉煌，高楼大厦的灯光倒映在江面上。'
      },
      {
        url: 'https://picsum.photos/id/30/800/600',
        title: '海边日落',
        date: '2023-08-10',
        description: '夕阳西下，金色的阳光洒在海面上，形成美丽的风景线。'
      },
      {
        url: 'https://picsum.photos/id/40/800/600',
        title: '森林探险',
        date: '2023-09-05',
        description: '深入森林内部，探索大自然的神秘与美丽。'
      },
      {
        url: 'https://picsum.photos/id/50/800/600',
        title: '山间云雾',
        date: '2023-10-18',
        description: '清晨的山间云雾缭绕，宛如仙境一般。'
      },
      {
        url: 'https://picsum.photos/id/60/800/600',
        title: '田园风光',
        date: '2023-11-30',
        description: '宁静的乡村田野，金黄色的麦田在微风中摇曳。'
      }
    ])
    
    // 打开图片查看器
    const openPhoto = (index) => {
      selectedPhoto.value = index
      document.body.style.overflow = 'hidden'
    }
    
    // 关闭图片查看器
    const closePhoto = () => {
      selectedPhoto.value = null
      document.body.style.overflow = 'auto'
    }
    
    // 滚动到顶部
    const scrollToTop = () => {
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      })
    }
    
    // 监听滚动事件
    const handleScroll = () => {
      showBackToTop.value = window.scrollY > 300
    }
    
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
      window.addEventListener('scroll', handleScroll)
      // 初始化滚动状态
      handleScroll()
      // 添加动画效果
      addScrollAnimation()
    })
    
    onUnmounted(() => {
      window.removeEventListener('scroll', handleScroll)
    })
    
    return {
      photos,
      selectedPhoto,
      showBackToTop,
      openPhoto,
      closePhoto,
      scrollToTop
    }
  }
}
</script>

<style scoped>
/* 页面容器 */
.photoalbum-container {
  min-height: 100vh;
  background-color: #0a0a0a;
  color: #fff;
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, sans-serif;
}

/* 页面头部 */
.page-header {
  text-align: center;
  padding: 120px 20px 60px;
  background-color: #0a0a0a;
  position: relative;
  overflow: hidden;
}

.page-header::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 1px;
  background: linear-gradient(90deg, transparent, #FF5A5F, transparent);
}

.page-header::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: radial-gradient(circle at center, rgba(255,90,95,0.1) 0%, rgba(10,10,10,0) 70%);
  pointer-events: none;
}

.page-title {
  font-size: clamp(2.5rem, 6vw, 4rem);
  margin-bottom: 15px;
  color: #fff;
  font-weight: 900;
  text-transform: uppercase;
  letter-spacing: 2px;
  position: relative;
  z-index: 1;
}

.page-subtitle {
  font-size: 1.2rem;
  color: #aaa;
  margin: 0;
  font-weight: 300;
  position: relative;
  z-index: 1;
}

/* 相册内容 */
.photoalbum-content {
  max-width: 1400px;
  margin: 0 auto;
  padding: 80px 20px;
  background-color: #0a0a0a;
  position: relative;
}

/* 图片画廊 */
.photo-gallery {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 30px;
}

/* 图片项 */
.photo-item {
  position: relative;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 5px 30px rgba(0,0,0,0.5);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  cursor: pointer;
  background-color: #111;
  border: 1px solid rgba(255,255,255,0.05);
}

.photo-item:hover {
  transform: translateY(-8px) scale(1.02);
  box-shadow: 0 15px 40px rgba(0,0,0,0.6), 0 0 0 1px rgba(255,90,95,0.3);
}

.photo-image {
  width: 100%;
  height: 280px;
  object-fit: cover;
  display: block;
  transition: transform 0.7s ease;
  filter: brightness(0.9) contrast(1.1);
}

.photo-item:hover .photo-image {
  transform: scale(1.08);
  filter: brightness(1) contrast(1.15);
}

.photo-overlay {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 25px;
  background: linear-gradient(to top, rgba(0,0,0,0.95), rgba(0,0,0,0.4), rgba(0,0,0,0));
  color: white;
  transition: padding 0.3s ease, background 0.3s ease;
}

.photo-item:hover .photo-overlay {
  padding-top: 50px;
  background: linear-gradient(to top, rgba(0,0,0,0.95), rgba(0,0,0,0.5), rgba(255,90,95,0.1));
}

.photo-title {
  font-size: 1.4rem;
  margin: 0 0 8px;
  font-weight: 600;
  text-shadow: 0 2px 10px rgba(0,0,0,0.5);
}

.photo-date {
  font-size: 0.95rem;
  margin: 0;
  opacity: 0.8;
  font-weight: 300;
}

/* 图片查看器模态框 */
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.95);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
  padding: 20px;
  animation: fadeIn 0.3s ease;
  backdrop-filter: blur(5px);
}

.modal-content {
  position: relative;
  max-width: 900px;
  max-height: 90vh;
  overflow: auto;
  background-color: #121212;
  border-radius: 8px;
  animation: slideIn 0.3s ease;
  border: 1px solid rgba(255,90,95,0.2);
  box-shadow: 0 20px 60px rgba(0,0,0,0.8);
}

.close-btn {
  position: absolute;
  top: 15px;
  right: 15px;
  font-size: 2rem;
  color: #fff;
  cursor: pointer;
  z-index: 10;
  background: rgba(255,90,95,0.8);
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.close-btn:hover {
  background: rgba(255,90,95,1);
  transform: rotate(90deg);
}

.modal-image {
  width: 100%;
  height: auto;
  display: block;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  filter: brightness(1.05);
}

.modal-info {
  padding: 30px;
}

.modal-info h2 {
  margin: 0 0 15px;
  font-size: 1.8rem;
  color: #fff;
  border-bottom: 1px solid rgba(255,90,95,0.3);
  padding-bottom: 10px;
}

.modal-info p {
  margin: 0 0 15px;
  color: #aaa;
  font-weight: 300;
}

.photo-description {
  font-size: 1.1rem;
  line-height: 1.8;
  color: #ccc;
  margin-top: 20px;
  font-style: italic;
}

/* 返回顶部按钮 */
.back-to-top {
  position: fixed;
  bottom: 30px;
  right: 30px;
  background-color: #FF5A5F;
  color: white;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  box-shadow: 0 4px 20px rgba(255,90,95,0.4);
  transition: all 0.3s ease;
  z-index: 100;
  opacity: 0;
  visibility: hidden;
}

.back-to-top.visible {
  opacity: 1;
  visibility: visible;
  animation: fadeInUp 0.5s ease;
}

.back-to-top:hover {
  background-color: #e54e53;
  transform: translateY(-8px) scale(1.1);
  box-shadow: 0 6px 30px rgba(255,90,95,0.6);
}

/* 动画 */
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px) scale(0.8);
  }
  to {
    opacity: 1;
    transform: translateY(0) scale(1);
  }
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

@keyframes slideIn {
  from {
    transform: translateY(-20px);
    opacity: 0;
  }
  to {
    transform: translateY(0);
    opacity: 1;
  }
}

/* 淡入动画类 */
.fade-in {
  opacity: 0;
  transform: translateY(20px);
  transition: opacity 0.8s ease-out, transform 0.8s ease-out;
}

/* 为不同元素设置不同的动画延迟 */
.page-title {
  transition-delay: 0.2s;
}

.page-subtitle {
  transition-delay: 0.4s;
}

.photo-item:nth-child(1) {
  transition-delay: 0.1s;
}

.photo-item:nth-child(2) {
  transition-delay: 0.2s;
}

.photo-item:nth-child(3) {
  transition-delay: 0.3s;
}

.photo-item:nth-child(4) {
  transition-delay: 0.4s;
}

.photo-item:nth-child(5) {
  transition-delay: 0.5s;
}

.photo-item:nth-child(6) {
  transition-delay: 0.6s;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .photo-gallery {
    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
    gap: 20px;
  }
  
  .photoalbum-content {
    padding: 40px 15px;
  }
  
  .page-header {
    padding: 80px 15px 40px;
  }
  
  .modal-content {
    max-width: 100%;
  }
}

/* 响应式设计 */
@media (max-width: 768px) {
  .page-header {
    padding: 100px 15px 50px;
  }
  
  .page-title {
    font-size: 2.2rem;
  }
  
  .photoalbum-content {
    padding: 50px 15px;
  }
  
  .photo-gallery {
    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
    gap: 20px;
  }
  
  .photo-image {
    height: 220px;
  }
  
  .modal {
    padding: 10px;
  }
  
  .modal-content {
    max-height: 95vh;
  }
  
  .modal-info {
    padding: 20px;
  }
  
  .back-to-top {
    bottom: 20px;
    right: 20px;
    width: 45px;
    height: 45px;
  }
}

@media (max-width: 480px) {
  .page-header {
    padding: 80px 15px 40px;
  }
  
  .page-title {
    font-size: 1.8rem;
  }
  
  .page-subtitle {
    font-size: 1rem;
  }
  
  .photo-gallery {
    grid-template-columns: 1fr;
  }
  
  .photo-image {
    height: 240px;
  }
  
  .modal-info h2 {
    font-size: 1.5rem;
  }
  
  .photo-description {
    font-size: 1rem;
  }
}
</style>