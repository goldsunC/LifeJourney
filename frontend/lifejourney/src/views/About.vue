<template>
  <div class="about-page">
    <!-- 页面标题区域 -->
    <section class="page-header">
      <div class="container">
        <h1 class="page-title fade-in">关于我</h1>
      </div>
    </section>

    <!-- 个人介绍区域 -->
    <section class="section about-section">
      <div class="container">
        <div class="about-content max-w-5xl mx-auto">
          <!-- 个人资料卡片 -->
          <div class="about-profile fade-in">
            <div class="profile-image">
              <img 
                src="https://images.unsplash.com/photo-1534528741775-53994a69daeb?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1200&q=80" 
                alt="Personal Photo" 
                class="profile-pic"
              >
            </div>
            <div class="profile-bio">
              <h4 class="text-2xl font-bold mb-4">你好，我是 <span class="text-accent">辰華昭昭</span></h4>
              <p class="text-gray-700 mb-4">
                欢迎来到我的 LifeJourney 个人主页！我是一位热爱生活、喜欢旅行和摄影的创作者。
                我创建这个平台是为了记录生活中的美好瞬间，分享我的成长历程和心得体会。
              </p>
              <p class="text-gray-700">
                我相信每个人的生命都是一段独特的旅程，值得被记录和珍视。通过这个平台，
                我希望能够连接更多志同道合的朋友，一起分享生活中的点滴感悟。
              </p>
            </div>
          </div>

          <!-- 兴趣爱好区域 -->
          <div class="interests fade-in">
            <h4 class="text-2xl font-bold mb-6">我的兴趣爱好</h4>
            <div class="interest-cards">
              <div class="interest-card">
                <div class="interest-icon">📸</div>
                <h5 class="text-lg font-semibold mb-2">摄影</h5>
                <p class="text-gray-600">捕捉生活中的美好瞬间，记录身边的感动与风景</p>
              </div>
              <div class="interest-card">
                <div class="interest-icon">✈️</div>
                <h5 class="text-lg font-semibold mb-2">旅行</h5>
                <p class="text-gray-600">探索世界各地的文化与风景，开阔视野与心灵</p>
              </div>
              <div class="interest-card">
                <div class="interest-icon">📝</div>
                <h5 class="text-lg font-semibold mb-2">写作</h5>
                <p class="text-gray-600">用文字记录思考与感悟，分享生活的点滴体验</p>
              </div>
              <div class="interest-card">
                <div class="interest-icon">🎨</div>
                <h5 class="text-lg font-semibold mb-2">艺术</h5>
                <p class="text-gray-600">欣赏与创作各种形式的艺术，丰富精神世界</p>
              </div>
            </div>
          </div>

          <!-- 随想随记区域 -->
          <div class="quotes-section fade-in">
            <h4 class="text-2xl font-bold mb-6">随想随记</h4>
            <div class="quote-cards">
              <div 
                v-for="(quote, index) in quotes" 
                :key="index" 
                class="quote-card"
                :style="{ animationDelay: `${index * 0.2}s` }"
              >
                <p class="quote-text">{{ quote.text }}</p>
                <p class="quote-author">— {{ quote.author || '未知作者' }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 随想随记数据
const quotes = ref([
  {
    text: "生活不是缺少美，而是缺少发现美的眼睛。在平凡的日子里，我们也能找到属于自己的小确幸。",
    author: "罗丹"
  },
  {
    text: "旅行的意义不在于目的地，而在于沿途的风景和看风景的心情。每一段旅程，都是一次成长。",
    author: "未知"
  },
  {
    text: "摄影是瞬间的艺术，也是永恒的记忆。按下快门的那一刻，我们留住了时光的痕迹。",
    author: "Elena"
  },
  {
    text: "人生就像一场旅行，不必在乎目的地，在乎的是沿途的风景以及看风景的心情。",
    author: "某本书"
  }
]);
</script>

<style scoped>
/* 页面标题区域 */
.page-header {
  background: linear-gradient(135deg, #121212 0%, #2a2a2a 100%);
  color: white;
  padding: 5rem 0 3rem;
  text-align: center;
}

.page-title {
  font-size: 3rem;
  font-weight: 800;
  margin: 0;
}

/* 关于区域 */
.about-section {
  padding: 1.5rem 0;
  background-color: #e9ecef;
}

/* 个人资料卡片 */
.about-profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2rem;
  margin-bottom: 5rem;
  padding: 2rem;
  background-color: #f0f2f5;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.profile-image {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  overflow: hidden;
  border: 4px solid white;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.profile-pic {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile-bio {
  text-align: center;
  max-width: 600px;
}

/* 强调文本样式 */
  .text-accent {
    color: #FF5A5F;
  }

  /* 兴趣爱好样式 */
  .interests {
    margin-bottom: 5rem;
  }

  .interest-cards {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 2rem;
  }

  .interest-card {
    background-color: #fff;
    padding: 2rem;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    text-align: center;
  }

  .interest-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
  }

  .interest-icon {
    font-size: 3rem;
    margin-bottom: 1rem;
  }

  /* 随想随记样式 */
  .quotes-section {
    margin-top: 3rem;
  }

  .quote-cards {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 2rem;
  }

  .quote-card {
    background-color: #fff;
    padding: 2rem;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
    border-left: 4px solid #FF5A5F;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }

  .quote-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
  }

  .quote-text {
    font-size: 1.1rem;
    font-style: italic;
    color: #444;
    margin-bottom: 1rem;
    line-height: 1.6;
  }

  .quote-author {
    text-align: right;
    font-weight: 600;
    color: #666;
  }

  /* 淡入动画 */
  .fade-in {
    opacity: 0;
    animation: fadeIn 1s forwards;
  }

  @keyframes fadeIn {
    from {
      opacity: 0;
      transform: translateY(20px);
    }
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }

  /* 响应式设计 */
  @media (min-width: 768px) {
    .about-profile {
      flex-direction: row;
      text-align: left;
    }

    .profile-bio {
      text-align: left;
    }
  }

  @media (max-width: 767px) {
    .page-title {
      font-size: 2rem;
    }

    .interest-cards,
    .quote-cards {
      grid-template-columns: 1fr;
    }

    .about-section {
      padding: 3rem 0;
    }

    .page-header {
      padding: 4rem 0 2rem;
    }

    .profile-image {
      width: 150px;
      height: 150px;
    }
}

/* 容器样式 */
  .container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 1.5rem;
    width: 100%;
  }

  /* 个人介绍区域样式 */
  .about-content {
    padding: 6rem 0 4rem;
    opacity: 0;
    animation: fadeIn 1s ease-out forwards;
  }

  .about-profile {
    display: grid;
    grid-template-columns: 1fr;
    gap: 2rem;
    margin-bottom: 4rem;
  }

  @media (min-width: 768px) {
    .about-profile {
      grid-template-columns: 1fr 2fr;
      align-items: center;
    }
  }

  .about-image {
    display: flex;
    justify-content: center;
  }

  .profile-pic-container {
    position: relative;
    width: 250px;
    height: 250px;
    animation: float 6s ease-in-out infinite;
  }

  .profile-pic {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    object-fit: cover;
    border: 5px solid white;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.15);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }

  .profile-pic:hover {
    transform: scale(1.03);
    box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
  }

  .about-bio {
    opacity: 0;
    animation: slideIn 1s ease-out 0.3s forwards;
  }

  .about-bio h2 {
    font-size: 2rem;
    margin-bottom: 1.5rem;
    color: white;
    text-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    font-weight: 700;
  }

  .about-bio p {
    margin-bottom: 1.25rem;
    line-height: 1.8;
    color: #f5f5f5;
    font-size: 1.05rem;
  }

  /* 动画效果 */
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
      opacity: 0;
      transform: translateY(20px);
    }
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }

  @keyframes float {
    0% {
      transform: translateY(0px);
    }
    50% {
      transform: translateY(-15px);
    }
    100% {
      transform: translateY(0px);
    }
  }

.about-social {
  display: flex;
  gap: 1rem;
  margin-top: 2rem;
}

.about-social a {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  background-color: rgba(255, 255, 255, 0.2);
  color: white;
  transition: all 0.3s ease;
}

.about-social a:hover {
  background-color: rgba(255, 255, 255, 0.4);
  transform: translateY(-3px);
}

/* 个人信息卡片样式 */
.about-info {
  display: grid;
  grid-template-columns: 1fr;
  gap: 2rem;
  margin-bottom: 4rem;
}

@media (min-width: 768px) {
  .about-info {
    grid-template-columns: repeat(3, 1fr);
  }
}

/* 删除不需要的info-card相关样式 */

/* 删除不需要的兴趣爱好样式 */

/* 个人理念样式 */
.about-philosophy {
  text-align: center;
  max-width: 800px;
  margin: 0 auto 4rem;
  padding: 2rem;
  border-radius: 8px;
}

.about-philosophy h2 {
  font-size: 2rem;
  margin-bottom: 1.5rem;
  color: white;
}

.about-philosophy blockquote {
  font-size: 1.25rem;
  font-style: italic;
  color: rgba(255, 255, 255, 0.9);
  margin-bottom: 1.5rem;
  padding: 1rem 2rem;
  position: relative;
}

.about-philosophy blockquote::before,
.about-philosophy blockquote::after {
  content: '"';
  font-size: 3rem;
  position: absolute;
  color: rgba(255, 255, 255, 0.2);
}

.about-philosophy blockquote::before {
  top: 0;
  left: 0;
}

.about-philosophy blockquote::after {
  bottom: -2rem;
  right: 0;
}

.about-philosophy p {
  color: rgba(255, 255, 255, 0.8);
  line-height: 1.8;
}

/* 删除不需要的联系区域和按钮样式 */

/* 留言板样式 */
  .quote-board {
    margin-top: 4rem;
    opacity: 0;
    animation: fadeIn 1s ease-out 0.5s forwards;
  }

  .quote-board-title {
    font-size: 1.75rem;
    margin-bottom: 2rem;
    text-align: center;
    color: white;
    text-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }

  .quote-cards {
    display: grid;
    grid-template-columns: 1fr;
    gap: 1.5rem;
  }

  @media (min-width: 768px) {
    .quote-cards {
      grid-template-columns: repeat(2, 1fr);
    }
  }

  @media (min-width: 1024px) {
    .quote-cards {
      grid-template-columns: repeat(4, 1fr);
    }
  }

  .quote-card {
    background: rgba(255, 255, 255, 0.8);
    border-radius: 12px;
    padding: 1.5rem;
    border: 1px solid rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease;
    opacity: 0;
    animation: slideIn 0.8s ease-out forwards;
    min-height: 200px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }

  .quote-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 15px 30px rgba(0, 0, 0, 0.15);
    background: rgba(255, 255, 255, 0.9);
  }

  .quote-content {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  .quote-text {
    font-size: 1.05rem;
    line-height: 1.6;
    color: #333;
    margin-bottom: 1rem;
    flex-grow: 1;
  }

  .quote-author {
    font-style: italic;
    font-size: 0.9rem;
    color: #666;
    text-align: right;
  }

  /* 响应式设计 */
  @media (max-width: 768px) {
    .about-content {
      padding: 4rem 0 2rem;
    }
    
    .profile-pic {
      width: 200px;
      height: 200px;
    }
    
    .about-bio h2 {
      font-size: 1.75rem;
      text-align: center;
    }
    
    .about-social {
      justify-content: center;
    }
    
    .about-philosophy {
      padding: 1rem;
    }
    
    .about-philosophy h2 {
      font-size: 1.75rem;
    }
    
    .about-philosophy blockquote {
      font-size: 1.125rem;
    }
    
    .quote-board {
      margin-top: 3rem;
    }
    
    .quote-board-title {
      font-size: 1.5rem;
    }
    
    .quote-cards {
      grid-template-columns: 1fr;
    }
    
    .quote-card {
      min-height: 180px;
    }
  }
</style>