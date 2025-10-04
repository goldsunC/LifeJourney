<template>
  <footer>
    <div class="container">
      <div class="footer-content">
        <div>
          <div class="footer-logo">
            LIFE<span class="text-accent">JOURNEY</span>
          </div>
          <p class="footer-text">
            LifeJourney，记录你的生命旅程，分享成长的点滴！
          </p>
          <div class="social-links">
            <a href="#" aria-label="Instagram">📷</a>
            <a href="#" aria-label="Twitter">🐦</a>
            <a href="#" aria-label="Facebook">📘</a>
            <a href="#" aria-label="Pinterest">📌</a>
          </div>
        </div>
        
        <div>
          <h4 class="footer-heading">Quick Links</h4>
          <div class="footer-links">
            <a href="#HOME">HOME</a>
            <a href="#TIMELINE">TIMELINE</a>
            <a href="#PHOTOALBUM">PHOTOALBUM</a>
            <a href="#WISHLIST">WISHLIST</a>
            <a href="#ABOUT">ABOUT</a>
          </div>
        </div>
        
        <div>
          <h4 class="footer-heading">Resources</h4>
          <div class="footer-links">
            <a href="#">Artist Statement</a>
            <a href="#">Press Kit</a>
            <a href="#">FAQs</a>
            <a href="#">Privacy Policy</a>
            <a href="#">Terms of Service</a>
          </div>
        </div>
      </div>
      
      <div class="footer-bottom">
        <p>&copy; 2023 Elena Mori. All rights reserved.</p>
      </div>
    </div>
  </footer>
</template>

<script setup>
// Footer component logic
</script>

<style scoped>
footer {
  background-color: #121212;
  color: white;
  padding: 3rem 0;
  margin-top: auto;
}

.footer-content {
  display: grid;
  grid-template-columns: 1fr;
  gap: 2rem;
  margin-bottom: 3rem;
}

@media (min-width: 768px) {
  .footer-content {
    grid-template-columns: 2fr 1fr 1fr;
  }
}

.footer-logo {
  font-size: 1.5rem;
  font-weight: 900;
  margin-bottom: 1rem;
}

.footer-text {
  color: #aaa;
  margin-bottom: 1.5rem;
  max-width: 400px;
}

.social-links {
  display: flex;
  gap: 1rem;
}

.social-links a {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 2.5rem;
  height: 2.5rem;
  border-radius: 50%;
  background-color: #222;
  color: #aaa;
  transition: all 0.3s ease;
  text-decoration: none;
}

.social-links a:hover {
  background-color: #D4AF37;
  color: white;
}

.footer-heading {
  font-size: 1.125rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  margin-bottom: 1rem;
}

.footer-links {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.footer-links a {
  color: #aaa;
  transition: color 0.3s ease;
  text-decoration: none;
}

.footer-links a:hover {
  color: #D4AF37;
}

.footer-bottom {
  padding-top: 2rem;
  border-top: 1px solid #222;
  text-align: center;
  color: #666;
  font-size: 0.875rem;
}

.container {
  width: 90%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

.text-accent {
  color: #D4AF37; /* 金色作为强调色 */
}
</style>
