<template>
  <div class="articles-page">
    <div class="container">
      <div class="page-header">
        <h1 class="page-title">文章与随想</h1>
        <p class="page-description">记录思想感悟、知识积累和生活点滴</p>
      </div>
      
      <div class="coming-soon">
        <div class="coming-soon-icon">📝</div>
        <h2>功能开发中</h2>
        <p>文章管理功能正在开发中，敬请期待...</p>
        <router-link to="/" class="btn btn-primary">返回首页</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
// Articles page logic
</script>

<style scoped>
.articles-page {
  padding: 120px 0 80px;
  min-height: calc(100vh - 140px);
}

.page-header {
  text-align: center;
  margin-bottom: 60px;
}

.page-title {
  font-size: 3rem;
  font-weight: 700;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin-bottom: 16px;
}

.page-description {
  font-size: 1.2rem;
  color: #666;
}

.coming-soon {
  text-align: center;
  padding: 80px 20px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
}

.coming-soon-icon {
  font-size: 4rem;
  margin-bottom: 24px;
}

.coming-soon h2 {
  font-size: 2rem;
  margin-bottom: 16px;
  color: #333;
}

.coming-soon p {
  color: #666;
  margin-bottom: 32px;
}

@media (max-width: 768px) {
  .articles-page {
    padding: 100px 0 60px;
  }
  
  .page-title {
    font-size: 2.5rem;
  }
}
</style>
