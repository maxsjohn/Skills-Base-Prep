TREE TRAVERSAL
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 CORE CONCEPT
• Traversal = visiting every node exactly once
• Order matters → different sequences for different purposes
• 3 main types: Pre, In, Post-order

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 PRE-ORDER (Root → Left → Right)
• Pattern: Process ROOT first, then children
• Memory trick: "ROOT comes PRE (before) children"
• Use cases:
  - Copy/clone tree structure
  - Prefix expressions  
  - Directory listing (folder first, then contents)

┌─────────────────────────────┐
│ Pseudocode:                 │
│ 1. Process current node     │
│ 2. Traverse left subtree    │
│ 3. Traverse right subtree   │
└─────────────────────────────┘

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 IN-ORDER (Left → Root → Right)
• Pattern: Process ROOT between children
• Memory trick: "ROOT is IN between left and right"
• ⭐ SPECIAL: For BST → gives SORTED sequence!
• Use cases:
  - Get sorted data from BST
  - Infix expressions (math notation)
  - Flattening BST to sorted array

┌─────────────────────────────┐
│ Pseudocode:                 │
│ 1. Traverse left subtree    │
│ 2. Process current node     │
│ 3. Traverse right subtree   │
└─────────────────────────────┘

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 POST-ORDER (Left → Right → Root)
• Pattern: Process ROOT after children
• Memory trick: "ROOT comes POST (after) children"
• ⭐ SPECIAL: Safe deletion order (children before parent)
• Use cases:
  - Delete entire tree safely
  - Calculate tree properties (height, size)
  - Postfix expressions (stack-based evaluation)
  - Directory size calculation

┌─────────────────────────────┐
│ Pseudocode:                 │
│ 1. Traverse left subtree    │
│ 2. Traverse right subtree   │
│ 3. Process current node     │
└─────────────────────────────┘

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 IMPLEMENTATION APPROACHES

🔄 RECURSIVE
• Pros: Clean, natural, matches tree structure
• Cons: Stack overflow for deep trees, uses O(h) space
• Best for: Learning, small-medium trees

🔄 ITERATIVE  
• Pros: No recursion limit, explicit stack control
• Cons: More complex code, need manual stack
• Best for: Production code, very deep trees

┌─────────────────────────────┐
│ Space Complexity:           │
│ • Recursive: O(h) - h=height│
│ • Iterative: O(h) - explicit│
│ Time: O(n) for both         │
└─────────────────────────────┘

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 QUICK IDENTIFICATION TRICK

Given tree:     A
               / \
              B   C
             / \
            D   E

• Pre-order: A B D E C (Root first at each level)
• In-order:  D B E A C (Left, Root, Right)  
• Post-order: D E B C A (Children first, then root)

Memory aid: "Pre = President first, In = In between, Post = Postpone root"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 REAL-WORLD APPLICATIONS

🌟 PRE-ORDER:
• File system backup (create folders first)
• HTML DOM parsing (parent tags first)
• Tree serialization

🌟 IN-ORDER:  
• BST to sorted array conversion
• Expression tree evaluation (infix)
• Range queries in BST

🌟 POST-ORDER:
• Calculate folder sizes (children first)
• Expression evaluation with stack
• Tree destruction/cleanup
• Dependency resolution (children before parent)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

⚠️ COMMON GOTCHAS

• **Null checks**: Always check if node exists before processing
• **Base case**: Recursion needs proper stopping condition  
• **Stack order**: For iterative, right child goes on stack first (LIFO)
• **BST property**: Only in-order gives sorted result for BST
• **Memory**: Deep trees can cause stack overflow in recursive approach

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

🔗 PATTERN RECOGNITION

• **Tree problems asking for order** → Choose appropriate traversal
• **Need sorted data from BST** → In-order
• **Building/copying tree** → Pre-order  
• **Calculating tree properties** → Post-order
• **Stack-based problems** → Consider iterative approach

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📖 PRACTICE CHECKLIST
□ Implement all 3 traversals recursively
□ Implement all 3 traversals iteratively  
□ Test with single node tree
□ Test with skewed tree (linked list like)
□ Test with balanced tree
□ Verify BST in-order gives sorted result

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

**Date:** [06/01/2025] | **Review:** [ ] Week 1 [ ] Month 1 [ ] Month 3