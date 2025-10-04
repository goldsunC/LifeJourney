<template>
  <div class="home">
    <!-- Hero Section -->
    <section class="hero">
      <div class="container">
        <div class="hero-content">
          <div class="hero-text">
            <h1 class="hero-title fade-in">
              记录生活的
              <span class="gradient-text">每一个精彩瞬间</span>
            </h1>
            <p class="hero-description fade-in">
              构建属于你的数字人生轨迹，珍藏回忆，规划未来，让每一段经历都成为成长的见证。
            </p>
            <div class="hero-actions fade-in">
              <router-link to="/timeline" class="btn btn-primary">
                开始记录
                <span>→</span>
              </router-link>
              <router-link to="/articles" class="btn btn-secondary">
                查看文章
              </router-link>
            </div>
          </div>
          <div class="hero-visual">
            <div class="floating-card card-1">
              <div class="card-icon">📅</div>
              <div class="card-text">时间线</div>
            </div>
            <div class="floating-card card-2">
              <div class="card-icon">📝</div>
              <div class="card-text">文章</div>
            </div>
            <div class="floating-card card-3">
              <div class="card-icon">📸</div>
              <div class="card-text">相册</div>
            </div>
            <div class="floating-card card-4">
              <div class="card-icon">🎯</div>
              <div class="card-text">目标</div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Stats Section -->
    <section class="stats-section section">
      <div class="container">
        <div class="stats-grid">
          <div class="stat-card card fade-in">
            <div class="stat-icon">📅</div>
            <div class="stat-number">{{ stats.timelineEvents }}</div>
            <div class="stat-label">时间线事件</div>
          </div>
          <div class="stat-card card fade-in">
            <div class="stat-icon">📝</div>
            <div class="stat-number">{{ stats.articles }}</div>
            <div class="stat-label">文章数量</div>
          </div>
          <div class="stat-card card fade-in">
            <div class="stat-icon">📸</div>
            <div class="stat-number">{{ stats.photos }}</div>
            <div class="stat-label">照片数量</div>
          </div>
          <div class="stat-card card fade-in">
            <div class="stat-icon">🎯</div>
            <div class="stat-number">{{ stats.goals }}</div>
            <div class="stat-label">目标数量</div>
          </div>
        </div>
      </div>
    </section>

    <!-- Features Section -->
    <section class="features-section section">
      <div class="container">
        <h2 class="section-title fade-in">核心功能</h2>
        <div class="features-grid grid grid-2">
          <div class="feature-card card fade-in">
            <div class="feature-icon">📅</div>
            <h3 class="feature-title">时间线</h3>
            <p class="feature-description">
              以时间轴的形式记录生命中的重要事件和成长里程碑，让每一段经历都有迹可循。
            </p>
            <router-link to="/timeline" class="feature-link">
              探索时间线 →
            </router-link>
          </div>
          
          <div class="feature-card card fade-in">
            <div class="feature-icon">📝</div>
            <h3 class="feature-title">文章与随想</h3>
            <p class="feature-description">
              记录思想感悟、知识积累和生活点滴的写作平台，让思考更有深度。
            </p>
            <router-link to="/articles" class="feature-link">
              开始写作 →
            </router-link>
          </div>
          
          <div class="feature-card card fade-in">
            <div class="feature-icon">📸</div>
            <h3 class="feature-title">相册影集</h3>
            <p class="feature-description">
              珍藏生活中的每一个精彩瞬间，按主题和时间组织你的珍贵回忆。
            </p>
            <router-link to="/gallery" class="feature-link">
              浏览相册 →
            </router-link>
          </div>
          
          <div class="feature-card card fade-in">
            <div class="feature-icon">🎯</div>
            <h3 class="feature-title">愿望清单</h3>
            <p class="feature-description">
              设定和跟踪个人目标，记录已实现的成就，让梦想照进现实。
            </p>
            <router-link to="/goals" class="feature-link">
              设定目标 →
            </router-link>
          </div>
        </div>
      </div>
    </section>

    <!-- Recent Activity Section -->
    <section class="recent-section section">
      <div class="container">
        <h2 class="section-title fade-in">最近动态</h2>
        <div class="recent-grid grid grid-3">
          <div class="recent-card card fade-in" v-for="item in recentActivity" :key="item.id">
            <div class="recent-type" :class="item.type">{{ item.typeIcon }}</div>
            <div class="recent-content">
              <h4 class="recent-title">{{ item.title }}</h4>
              <p class="recent-description">{{ item.description }}</p>
              <div class="recent-meta">
                <span class="recent-date">{{ item.date }}</span>
                <span class="recent-category">{{ item.category }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- CTA Section -->
    <section class="cta-section section">
      <div class="container">
        <div class="cta-content card fade-in">
          <h2 class="cta-title">开始你的数字人生之旅</h2>
          <p class="cta-description">
            加入我们，开始记录和规划你的精彩人生。每一个瞬间都值得被珍藏。
          </p>
          <div class="cta-actions">
            <router-link to="/timeline" class="btn btn-primary">
              立即开始
              <span>🚀</span>
            </router-link>
            <router-link to="/articles" class="btn btn-secondary">
              了解更多
            </router-link>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref } from 'vue'

// 统计数据
const stats = ref({
  timelineEvents: 24,
  articles: 18,
  photos: 156,
  goals: 12
})

// 最近活动数据
const recentActivity = ref([
  {
    id: 1,
    type: 'timeline',
    typeIcon: '📅',
    title: '完成了Vue 3项目',
    description: '成功构建了个人生活记录平台的前端应用',
    date: '2024-01-15',
    category: '职业发展'
  },
  {
    id: 2,
    type: 'article',
    typeIcon: '📝',
    title: '关于技术成长的思考',
    description: '分享在软件开发过程中的学习心得和成长感悟',
    date: '2024-01-12',
    category: '思考感悟'
  },
  {
    id: 3,
    type: 'photo',
    typeIcon: '📸',
    title: '新年旅行照片',
    description: '记录了新年假期的美好时光和难忘回忆',
    date: '2024-01-10',
    category: '旅行生活'
  },
  {
    id: 4,
    type: 'goal',
    typeIcon: '🎯',
    title: '学习新的编程语言',
    description: '计划在今年掌握Rust编程语言',
    date: '2024-01-08',
    category: '技能提升'
  },
  {
    id: 5,
    type: 'article',
    typeIcon: '📝',
    title: '时间管理的重要性',
    description: '探讨如何更好地管理和利用时间',
    date: '2024-01-05',
    category: '生活管理'
  },
  {
    id: 6,
    type: 'photo',
    typeIcon: '📸',
    title: '工作环境照片',
    description: '展示我的工作空间和开发环境',
    date: '2024-01-03',
    category: '工作生活'
  }
])
</script>

<style scoped>
.home {
  padding-top: 70px;
}

/* Hero Section */
.hero {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 120px 0;
  position: relative;
  overflow: hidden;
}

.hero-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 60px;
  align-items: center;
}

.hero-title {
  font-size: 3.5rem;
  font-weight: 800;
  line-height: 1.2;
  margin-bottom: 24px;
}

.gradient-text {
  background: linear-gradient(135deg, #ffeaa7 0%, #fab1a0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.hero-description {
  font-size: 1.25rem;
  opacity: 0.9;
  line-height: 1.6;
  margin-bottom: 40px;
}

.hero-actions {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}

.hero-visual {
  position: relative;
  height: 400px;
}

.floating-card {
  position: absolute;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.2);
  border-radius: 16px;
  padding: 20px;
  text-align: center;
  color: white;
  animation: float 6s ease-in-out infinite;
}

.card-1 {
  top: 20px;
  left: 20px;
  animation-delay: 0s;
}

.card-2 {
  top: 80px;
  right: 40px;
  animation-delay: 1.5s;
}

.card-3 {
  bottom: 80px;
  left: 60px;
  animation-delay: 3s;
}

.card-4 {
  bottom: 20px;
  right: 20px;
  animation-delay: 4.5s;
}

.card-icon {
  font-size: 2rem;
  margin-bottom: 8px;
}

.card-text {
  font-size: 0.9rem;
  font-weight: 500;
}

@keyframes float {
  0%, 100% {
    transform: translateY(0px);
  }
  50% {
    transform: translateY(-20px);
  }
}

/* Stats Section */
.stats-section {
  background: white;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 30px;
}

.stat-card {
  text-align: center;
  padding: 40px 20px;
}

.stat-icon {
  font-size: 3rem;
  margin-bottom: 16px;
}

.stat-number {
  font-size: 3rem;
  font-weight: 800;
  color: #667eea;
  margin-bottom: 8px;
}

.stat-label {
  color: #666;
  font-weight: 500;
}

/* Features Section */
.features-section {
  background: #f8f9fa;
}

.features-grid {
  gap: 40px;
}

.feature-card {
  padding: 40px 30px;
  text-align: center;
  transition: all 0.3s ease;
}

.feature-icon {
  font-size: 4rem;
  margin-bottom: 24px;
}

.feature-title {
  font-size: 1.5rem;
  font-weight: 700;
  margin-bottom: 16px;
  color: #333;
}

.feature-description {
  color: #666;
  line-height: 1.6;
  margin-bottom: 24px;
}

.feature-link {
  color: #667eea;
  text-decoration: none;
  font-weight: 600;
  transition: all 0.3s ease;
}

.feature-link:hover {
  color: #764ba2;
  padding-left: 8px;
}

/* Recent Activity Section */
.recent-section {
  background: white;
}

.recent-grid {
  gap: 30px;
}

.recent-card {
  padding: 24px;
  display: flex;
  gap: 16px;
  align-items: flex-start;
}

.recent-type {
  width: 50px;
  height: 50px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
  flex-shrink: 0;
}

.recent-type.timeline {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.recent-type.article {
  background: linear-gradient(135deg, #ffeaa7 0%, #fab1a0 100%);
}

.recent-type.photo {
  background: linear-gradient(135deg, #74b9ff 0%, #0984e3 100%);
}

.recent-type.goal {
  background: linear-gradient(135deg, #00b894 0%, #00a085 100%);
}

.recent-content {
  flex: 1;
}

.recent-title {
  font-size: 1.1rem;
  font-weight: 600;
  margin-bottom: 8px;
  color: #333;
}

.recent-description {
  color: #666;
  font-size: 0.9rem;
  line-height: 1.5;
  margin-bottom: 12px;
}

.recent-meta {
  display: flex;
  gap: 16px;
  font-size: 0.8rem;
  color: #999;
}

.recent-category {
  background: #f0f0f0;
  padding: 2px 8px;
  border-radius: 12px;
}

/* CTA Section */
.cta-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.cta-content {
  background: white;
  padding: 60px 40px;
  text-align: center;
  max-width: 800px;
  margin: 0 auto;
}

.cta-title {
  font-size: 2.5rem;
  font-weight: 700;
  margin-bottom: 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.cta-description {
  font-size: 1.2rem;
  color: #666;
  margin-bottom: 40px;
  line-height: 1.6;
}

.cta-actions {
  display: flex;
  gap: 20px;
  justify-content: center;
  flex-wrap: wrap;
}

/* Responsive Design */
@media (max-width: 768px) {
  .hero {
    padding: 80px 0;
  }
  
  .hero-content {
    grid-template-columns: 1fr;
    gap: 40px;
    text-align: center;
  }
  
  .hero-title {
    font-size: 2.5rem;
  }
  
  .hero-visual {
    height: 300px;
  }
  
  .floating-card {
    padding: 15px;
  }
  
  .card-icon {
    font-size: 1.5rem;
  }
  
  .card-text {
    font-size: 0.8rem;
  }
  
  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }
  
  .stat-number {
    font-size: 2.5rem;
  }
  
  .feature-card {
    padding: 30px 20px;
  }
  
  .cta-content {
    padding: 40px 20px;
  }
  
  .cta-title {
    font-size: 2rem;
  }
  
  .cta-actions {
    flex-direction: column;
    align-items: center;
  }
}
</style>
