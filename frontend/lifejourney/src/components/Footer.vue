<template>
  <footer class="footer">
    <div class="container">
      <div class="footer-content">
        <div class="footer-section">
          <h3 class="footer-title">LifeJourney</h3>
          <p class="footer-description">
            记录生活的每一个精彩瞬间，构建属于你的数字人生轨迹。
          </p>
          <div class="social-links">
            <a href="#" class="social-link">📧</a>
            <a href="#" class="social-link">🐙</a>
            <a href="#" class="social-link">📱</a>
          </div>
        </div>
        
        <div class="footer-section">
          <h4 class="footer-subtitle">功能模块</h4>
          <ul class="footer-links">
            <li><router-link to="/timeline">时间线</router-link></li>
            <li><router-link to="/articles">文章随想</router-link></li>
            <li><router-link to="/gallery">相册影集</router-link></li>
            <li><router-link to="/goals">愿望清单</router-link></li>
          </ul>
        </div>
        
        <div class="footer-section">
          <h4 class="footer-subtitle">关于项目</h4>
          <ul class="footer-links">
            <li><a href="#">项目介绍</a></li>
            <li><a href="#">技术栈</a></li>
            <li><a href="#">开源协议</a></li>
            <li><a href="#">贡献指南</a></li>
          </ul>
        </div>
        
        <div class="footer-section">
          <h4 class="footer-subtitle">联系方式</h4>
          <ul class="footer-links">
            <li><a href="#">意见反馈</a></li>
            <li><a href="#">问题报告</a></li>
            <li><a href="#">功能建议</a></li>
            <li><a href="#">技术支持</a></li>
          </ul>
        </div>
      </div>
      
      <div class="footer-bottom">
        <div class="footer-bottom-content">
          <p class="copyright">
            © 2024 LifeJourney. Made with ❤️ for personal growth.
          </p>
          <div class="footer-bottom-links">
            <a href="#">隐私政策</a>
            <a href="#">使用条款</a>
            <a href="#">Cookie设置</a>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup>
// Footer component logic
</script>

<style scoped>
.footer {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  margin-top: auto;
}

.footer-content {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr 1fr;
  gap: 40px;
  padding: 60px 0 40px;
}

.footer-section h3,
.footer-section h4 {
  margin-bottom: 20px;
}

.footer-title {
  font-size: 1.5rem;
  font-weight: 700;
}

.footer-subtitle {
  font-size: 1.1rem;
  font-weight: 600;
  opacity: 0.9;
}

.footer-description {
  opacity: 0.8;
  line-height: 1.6;
  margin-bottom: 24px;
}

.social-links {
  display: flex;
  gap: 16px;
}

.social-link {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 40px;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 50%;
  text-decoration: none;
  font-size: 1.2rem;
  transition: all 0.3s ease;
}

.social-link:hover {
  background: rgba(255, 255, 255, 0.2);
  transform: translateY(-2px);
}

.footer-links {
  list-style: none;
}

.footer-links li {
  margin-bottom: 12px;
}

.footer-links a {
  color: rgba(255, 255, 255, 0.8);
  text-decoration: none;
  transition: all 0.3s ease;
}

.footer-links a:hover {
  color: white;
  padding-left: 8px;
}

.footer-bottom {
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  padding: 24px 0;
}

.footer-bottom-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 20px;
}

.copyright {
  opacity: 0.8;
  margin: 0;
}

.footer-bottom-links {
  display: flex;
  gap: 24px;
}

.footer-bottom-links a {
  color: rgba(255, 255, 255, 0.8);
  text-decoration: none;
  font-size: 0.9rem;
  transition: all 0.3s ease;
}

.footer-bottom-links a:hover {
  color: white;
}

@media (max-width: 768px) {
  .footer-content {
    grid-template-columns: 1fr;
    gap: 30px;
    padding: 40px 0 30px;
  }
  
  .footer-bottom-content {
    flex-direction: column;
    text-align: center;
  }
  
  .footer-bottom-links {
    flex-wrap: wrap;
    justify-content: center;
  }
}
</style>
