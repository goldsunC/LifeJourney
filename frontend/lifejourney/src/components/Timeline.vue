<template>
  <section class="section bg-white timeline-section">
    <div class="container">
      <div class="max-w-3xl mx-auto text-center mb-16 fade-in">
        <h2 class="text-sm uppercase tracking-widest text-accent mb-4">Professional Journey</h2>
        <h3 class="text-3xl font-bold mb-6">
          Career Timeline
        </h3>
        <p class="text-gray-700">
          A chronological overview of key milestones in Elena Mori's artistic career.
        </p>
      </div>
      
      <div class="career-timeline">
        <!-- 时间线条目 -->
        <div 
          v-for="(entry, index) in timelineEntries" 
          :key="index"
          class="timeline-entry fade-in"
        >
          <div class="timeline-dot"></div>
          <div class="timeline-year">{{ entry.year }}</div>
          <h4 class="text-xl font-bold mb-2">{{ entry.title }}</h4>
          <p class="text-gray-600">{{ entry.description }}</p>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'Timeline',
  data() {
    return {
      timelineEntries: [
        {
          year: '1985',
          title: 'Born in Milan, Italy',
          description: 'Raised in a multicultural family of artists and architects'
        },
        {
          year: '2004-2008',
          title: 'Accademia di Belle Arti di Brera',
          description: 'Studied traditional painting and sculpture in Milan'
        },
        {
          year: '2008-2009',
          title: 'Royal College of Art, London',
          description: 'MFA in Contemporary Art Practice, graduated with distinction'
        },
        {
          year: '2009',
          title: 'First Solo Exhibition',
          description: '"Emergence" at Gallery 1957, London'
        },
        {
          year: '2010-2014',
          title: 'Berlin Studio Residency',
          description: 'Developed early mixed-media and digital works'
        },
        {
          year: '2015',
          title: 'Move to New York',
          description: 'Established permanent studio in Brooklyn Arts District'
        },
        {
          year: '2017',
          title: 'Major Solo Exhibition',
          description: '"Perceptual Fields" at MoMA PS1, New York'
        },
        {
          year: '2019',
          title: 'Tokyo Studio Opening',
          description: 'Established second studio in Tokyo\'s Art Triangle'
        },
        {
          year: '2021',
          title: 'International Recognition',
          description: 'Awarded the prestigious International Art Prize for Digital Innovation'
        },
        {
          year: '2023-Present',
          title: 'Global Exhibitions',
          description: 'Major retrospectives in London, Tokyo, and New York'
        }
      ]
    }
  },
  mounted() {
    // 添加滚动动画效果
    this.addScrollAnimation();
  },
  methods: {
    addScrollAnimation() {
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
  }
}
</script>

<style scoped>
/* 基础样式 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.container {
  width: 90%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

.section {
  padding: 6rem 0;
}

.text-accent {
  color: #D4AF37; /* 金色作为强调色 */
}

.bg-white {
  background-color: white;
}

.text-sm {
  font-size: 0.875rem;
}

.text-xl {
  font-size: 1.5rem;
}

.text-3xl {
  font-size: 2.5rem;
}

.font-bold {
  font-weight: 700;
}

.uppercase {
  text-transform: uppercase;
}

.tracking-widest {
  letter-spacing: 0.25em;
}

.mb-4 {
  margin-bottom: 1rem;
}

.mb-6 {
  margin-bottom: 1.5rem;
}

.mb-16 {
  margin-bottom: 4rem;
}

.max-w-3xl {
  max-width: 54rem;
}

.mx-auto {
  margin-left: auto;
  margin-right: auto;
}

.text-center {
  text-align: center;
}

.text-gray-700 {
  color: #374151;
}

.text-gray-600 {
  color: #4B5563;
}

/* 时间线特定样式 */
.timeline-section {
  position: relative;
  padding: 4rem 0;
}

.career-timeline {
  position: relative;
  max-width: 1000px;
  margin: 0 auto;
}

.career-timeline::before {
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  left: 50%;
  width: 4px;
  background-color: #e0e0e0;
  transform: translateX(-50%);
}

.timeline-entry {
  padding: 1rem 0;
  position: relative;
  width: 50%;
  box-sizing: border-box;
}

.timeline-entry:nth-child(odd) {
  left: 0;
  padding-right: 4rem;
  text-align: right;
}

.timeline-entry:nth-child(even) {
  left: 50%;
  padding-left: 4rem;
}

.timeline-dot {
  position: absolute;
  width: 20px;
  height: 20px;
  background-color: #D4AF37;
  border-radius: 50%;
  top: 24px;
  z-index: 1;
}

.timeline-entry:nth-child(odd) .timeline-dot {
  right: -10px;
}

.timeline-entry:nth-child(even) .timeline-dot {
  left: -10px;
}

.timeline-year {
  font-size: 1.25rem;
  font-weight: 600;
  color: #D4AF37;
  margin-bottom: 0.5rem;
}

/* 动画效果 */
.fade-in {
  opacity: 0;
  transform: translateY(20px);
  transition: opacity 0.8s ease-out, transform 0.8s ease-out;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .career-timeline::before {
    left: 30px;
  }
  
  .timeline-entry {
    width: 100%;
    padding-left: 70px;
    padding-right: 25px;
    left: 0 !important;
    text-align: left !important;
  }
  
  .timeline-entry .timeline-dot {
    left: 20px !important;
    right: auto !important;
  }
}
</style>